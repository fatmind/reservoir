package com.fatmind.reservoir.degrade;

public interface DegradePostHandler {
	
	public Object handler(DegradeEntry degradeEntry);
	
}
