package com.fatmind.reservoir.flow;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class FlowInterceptor {
	
	private static Logger log = LoggerFactory.getLogger(FlowInterceptor.class);
	
	/**
	 * 流控配置器
	 */
	private FlowConfigurator flowConfigurator;
	
	
	@Around("@annotation(com.fatmind.reservoir.flow.Flow)")
	public Object flowInterceptor(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Method sourceMethod = joinPoint.getSignature().getClass().getMethod("getMethod");
		sourceMethod.setAccessible(true);
		Method method = (Method)sourceMethod.invoke(joinPoint.getSignature());
		Flow flowInterceptor = method.getAnnotation(com.fatmind.reservoir.flow.Flow.class);
		String key = flowInterceptor.key();
		if(StringUtils.isBlank(key)) {
			key = method.getDeclaringClass().getName() +  "." + method.getName(); 	//TODO 考虑模仿log4j package继承，简化配置
		}
		
		String from = null; 	//TODO 如何获取 ？
		if(flowConfigurator.isReject(from)) {
			log.info("invoker is reject, from = " + from);
			return null;
		}
		
		FlowEntry flowEntry = flowConfigurator.getEntry(key);
		if(flowEntry == null) {
			log.info("dont find maping flow entry, key = " + key);
			return joinPoint.proceed(joinPoint.getArgs());
		}
		
		/*
		 * 若需流控，交由后置处理器处理
		 */
		if(flowEntry.getFlowStrategy().isNeedBlocked(flowEntry, from)) {
			log.info("req is blocked, key = " + key);
			return flowEntry.getHandler().handler(flowEntry);
		} 
		/*
		 * 若无需流控，计数和调用原始方法
		 */
		else {
			flowEntry.getCounter().getAndIncrement();
			log.info("normal invoke, key = " + key + ", count = " + flowEntry.getCounter().get());
			try {
				return joinPoint.proceed(joinPoint.getArgs());
			} finally {
				flowEntry.getCounter().decrementAndGet();
			}
		}
	}


	public void setFlowConfigurator(FlowConfigurator flowConfigurator) {
		this.flowConfigurator = flowConfigurator;
	}
	
}
