package com.fatmind.reservoir;

import java.util.List;

public interface Configurator<T> {
	
	/**
	 * 根据Key获取Entry
	 * @param key
	 * @return T
	 */
	public T getEntry(String key);
	
	/**
	 * 获取所有Entry
	 * @return List<T>
	 */
	public List<T> getEntries();
	
	
}
