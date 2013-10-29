package com.fatmind.reservoir.degrade;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.fatmind.reservoir.Configurator;

/**
 * 服务降级拦截器. 支持：annotation与beanname/beantype 
 * @author bohan.sj
 */

@Aspect
public class DegradeInterceptor implements MethodInterceptor {

	private Configurator<DegradeEntry> configurator;
	private StatisticsScheduler statistics;
	private Knocker knocker = new Knocker();
	
	public DegradeInterceptor(Configurator<DegradeEntry> configurator) {
		this.configurator = configurator;
		this.statistics = new StatisticsScheduler(configurator);
	}
	
	@Override
	public Object invoke(MethodInvocation arg) throws Throwable {
		return handlerInterceptor(arg.getMethod(), arg.getThis(), arg.getArguments());
	}
	
	@Around("@annotation(com.fatmind.reservoir.degrade.Degrade)")
	public Object aspectInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
		Method sourceMethod = joinPoint.getSignature().getClass().getMethod("getMethod");
		sourceMethod.setAccessible(true);
		Method method = (Method)sourceMethod.invoke(joinPoint.getSignature());
		return handlerInterceptor(method, joinPoint.getTarget(), joinPoint.getArgs());
	}
	
	
	private Object handlerInterceptor(Method method, Object obj, Object... args) throws Throwable {
		Degrade degrade = method.getAnnotation(Degrade.class);
		String key = degrade.key();
		if(StringUtils.isBlank(key)) {
			key = method.getDeclaringClass().getName() +  "." + method.getName();	//TODO 考虑模仿log4j package继承，简化配置
		}
		
		DegradeEntry degradeEntry = configurator.getEntry(key);
		if(degradeEntry == null) {
			return method.invoke(obj, args);
		}
		
		if(degradeEntry.isOff()) {
			return degradeEntry.getPostHandler().handler(degradeEntry);
		}
		
		if(degradeEntry.isDegrade()) {
			knocker.tryKnock(degradeEntry, method, obj, args);
			return degradeEntry.getPostHandler().handler(degradeEntry);
		}
		
		return invoke(degradeEntry, method, obj, args);
	}
	
	private Object invoke(DegradeEntry degradeEntry, Method m, Object obj, Object...args) throws Throwable {
		long startTime = System.currentTimeMillis();
		boolean execRes = true;
		try {
			return m.invoke(obj, args);
		} catch (Throwable e) {	//TODO 允许用户自定义判断
			execRes = false;
			throw e;
		} finally {
			track(degradeEntry, System.currentTimeMillis() - startTime, execRes);
		}
	}
	
	/**
	 * 记录单次数据
	 * @param degradeEntry
	 * @param rt
	 * @param execRes
	 */
	public void track(DegradeEntry degradeEntry, long rt, boolean execRes) {
		Counter counter = degradeEntry.getCounter();
		counter.getReq().incrementAndGet();
		counter.getRt().addAndGet(rt);
		if(!execRes) counter.getFail().incrementAndGet(); 
	}
	
	/**
	 * 当服务被降级后，随机实际去请求后端，实现服务自动恢复
	 * @author fatmind
	 */
	class Knocker {
		
		private BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000);
	 	private ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 60, TimeUnit.MINUTES, queue);
	 	private Random random = new Random();
	 	
		public void tryKnock(final DegradeEntry degradeEntry, 
				final Method m, final Object obj, final Object...args) {
			int luckyNum = random.nextInt(100);
			if(luckyNum >= 99) {	// 默认仅释放1%流量
				executor.submit(new Runnable() {
					@Override
					public void run() {
						try {
							invoke(degradeEntry, m, obj, args);
						} catch (Throwable e) {
							// ingore all exception
						}
					}
				});
			}
		}
	}
	
}
