package com.fatmind.reservoir.degrade;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * 服务降级拦截器. 支持：annotation与beanname 
 * @author bohan.sj
 */

@Aspect
public class DegradeInterceptor implements MethodInterceptor {

	private DegradeConfigurator configurator;
	private Statistics statistics;
	
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
			key = method.getDeclaringClass().getName() +  "." + method.getName();
		}
		
		DegradeEntry degradeEntry = configurator.getEntry(key);
		if(degradeEntry == null) {
			return method.invoke(obj, args);
		}
		
		TrackDO track = new TrackDO();
		long startTime = System.currentTimeMillis();
		try {
			return method.invoke(obj, args);
		} catch (Throwable e) {
			track.setSuccess(false);	//TODO 是否失败判断允许扩展
		} finally {
			track.setRt(System.currentTimeMillis() - startTime);
			statistics.addTrack(track);
		}
		return null;
	}
}