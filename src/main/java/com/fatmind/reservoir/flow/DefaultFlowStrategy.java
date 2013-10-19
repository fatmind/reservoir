package com.fatmind.reservoir.flow;

/**
 * 默认流控策略. 1.属于黑名单则流控    2.超过阈值则流控
 * @author fatmind
 */
public class DefaultFlowStrategy implements FlowStrategy {

	@Override
	public boolean isNeedBlocked(FlowEntry flowEntry, String from) {
		
		/*
		 * 若属于黑名单，需要流控
		 */
		if(flowEntry.getBlackNames() != null 
				&& flowEntry.getBlackNames().contains(from)) {
			return true;
		}
		
		/*
		 * 若累计处理中请求量超过阈值，需要流控
		 */
		if(flowEntry.getCounter().get() > flowEntry.getThreshold()) {
			return true;
		}
		
		return false;
	}
	
	

}
