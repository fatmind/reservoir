package com.fatmind.reservoir.flow.example.degrade;

import com.fatmind.reservoir.degrade.DegradeConfigurator;
import com.fatmind.reservoir.degrade.DegradeEntry;

public class MockDegradeConfigurator implements DegradeConfigurator {

	@Override
	public DegradeEntry getEntry(String key) {
		DegradeEntry degradeEntry = new DegradeEntry();
		degradeEntry.setRt(150);
		degradeEntry.setFailureRate(0.8);
		return degradeEntry;
	}

}
