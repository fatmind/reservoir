package com.fatmind.reservoir.degrade;

import com.fatmind.reservoir.PostHandler;

/**
 * 默认实现，仅返回Null
 * @author fatmind
 */
public class DefaultDegradePostHandler implements PostHandler<DegradeEntry> {

	@Override
	public Object handler(DegradeEntry degradeEntry) {
		return null;
	}

}
