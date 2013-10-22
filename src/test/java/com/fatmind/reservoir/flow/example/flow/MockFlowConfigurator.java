package com.fatmind.reservoir.flow.example.flow;

import com.fatmind.reservoir.flow.FlowConfigurator;
import com.fatmind.reservoir.flow.FlowEntry;

public class MockFlowConfigurator implements FlowConfigurator {

	private FlowEntry defaultFlowEntry = null;
	
	public MockFlowConfigurator() {
		defaultFlowEntry = new FlowEntry();
		defaultFlowEntry.setKey(ItemService.class.getName() + "." + "queryItemById");
		defaultFlowEntry.setThreshold(5);
	}
	
	@Override
	public FlowEntry getFlowEntry(String key) {
		return defaultFlowEntry;
	}

	@Override
	public boolean isRefuse(String from) {
		return false;
	}

}
