package com.fatmind.reservoir.flow;

/**
 * 默认实现，仅返回NULL 
 * @author fatmind
 */
public class DefaultFlowPostHandler implements FlowPostHandler {

	public Object handler(FlowEntry flowEntry) {
		return null;
	}

}
