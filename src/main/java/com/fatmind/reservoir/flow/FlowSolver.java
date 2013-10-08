package com.fatmind.reservoir.flow;

/**
 * 定义：被流控后，如何处理
 * @author fatmind
 */
public interface FlowSolver {
	
	public Object handler(FlowEntry flowEntry);
	
}
