package com.fatmind.reservoir.example.degrade;

import java.util.List;

import com.fatmind.reservoir.Configurator;
import com.fatmind.reservoir.degrade.DegradeEntry;

public class MockDegradeConfigurator implements Configurator<DegradeEntry> {

	@Override
	public DegradeEntry getEntry(String key) {
		DegradeEntry degradeEntry = new DegradeEntry();
		degradeEntry.setRt(150);
		degradeEntry.setFailureRate(0.8);
		return degradeEntry;
	}

	@Override
	public List<DegradeEntry> getEntries() {
		return null;
	}

}
