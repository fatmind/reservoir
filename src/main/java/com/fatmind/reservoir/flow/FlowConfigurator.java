package com.fatmind.reservoir.flow;


/**
 * 流控配置器
 * @author fatmind
 */
public interface FlowConfigurator {
	
	/**
	 * 根据Key获取FlowEntry
	 * @param key
	 * @return FlowEntry
	 */
	public FlowEntry getFlowEntry(String key);
	
	/**
	 * 根据来源判断, 是否拒绝服务	 //TODO 方法名待商榷
	 * @param from
	 * @return boolean
	 */
	public boolean isRefuse(String from);
	
}
