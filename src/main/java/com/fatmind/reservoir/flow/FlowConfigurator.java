package com.fatmind.reservoir.flow;

import com.fatmind.reservoir.Configurator;

public interface FlowConfigurator extends Configurator<FlowEntry> {
	
	/**
	 * 根据From判断是否拒绝服务
	 * @param from
	 * @return boolean
	 */
	public boolean isReject(String from);
	
}
