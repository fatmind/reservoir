package com.fatmind.reservoir.flow;

import java.util.List;
import java.util.Map;

/**
 * 流控配置器
 * @author fatmind
 */
public interface FlowConfigurator {
	
	/**
	 * 初始化流控入口配置
	 * @return Map<String, FlowEntry>
	 * @throws Exception
	 */
	public Map<String, FlowEntry> initFlowEntry() throws Exception;
	
	/**
	 * 
	 * @return
	 */
	public List<String> getFlowBlackName();
	
}
