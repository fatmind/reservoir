package com.fatmind.reservoir.flow;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class FlowInterceptor {
	
	private Logger log = Logger.getLogger(FlowInterceptor.class);
	
	/**
	 * ����������
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
			key = method.getDeclaringClass().getName() +  "." + method.getName();
		}
		
		String from = null;	//FIXME ��λ�ȡfrom����
		if(flowConfigurator.isRefuse(from)) {
			log.info("refuse service, from = " + from);
			return null;
		}
		
		FlowEntry flowEntry = flowConfigurator.getFlowEntry(key);
		if(flowEntry == null) {
			log.info("dont find maping flow entry, key = " + key);
			return joinPoint.proceed(joinPoint.getArgs());
		}
		
		/*
		 * �������أ����ɺ��ô���������
		 */
		if(flowEntry.getFlowStrategy().isNeedBlocked(flowEntry, from)) {
			log.info("req is blocked, key = " + key);
			return flowEntry.getHandler().handler(flowEntry);
		} 
		/*
		 * ���������أ������͵���ԭʼ����
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
