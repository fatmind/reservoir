package com.fatmind.reservoir.flow;

/**
 * 流控策略
 * @author fatmind
 */
public interface FlowStrategy {

	/**
	 * 是否需要流控
	 * @param flowEntry
	 * @param from 来源标识
	 * @return boolean
	 */
	public boolean isNeedBlocked(FlowEntry flowEntry, String from);
	
}
