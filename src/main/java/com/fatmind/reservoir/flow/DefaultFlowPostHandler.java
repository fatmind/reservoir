package com.fatmind.reservoir.flow;

import com.fatmind.reservoir.PostHandler;

/**
 * 默认实现，仅返回NULL 
 * @author fatmind
 */
public class DefaultFlowPostHandler implements PostHandler<FlowEntry> {

	public Object handler(FlowEntry flowEntry) {
		return null;
	}

}
