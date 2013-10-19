package com.fatmind.reservoir.flow;

/**
 * 流控后置处理器. 由使用者定义被流控后，如何处理，默认返回Null
 * @author fatmind
 */
public interface FlowPostHandler {
	
	public Object handler(FlowEntry flowEntry);
	
}
