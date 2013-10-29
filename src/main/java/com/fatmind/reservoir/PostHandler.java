package com.fatmind.reservoir;


/**
 * 后置处理器. 由使用者定义被流控或降级后，如何处理，默认返回Null
 * @author fatmind
 * @param <T>
 */
public interface PostHandler<T> {
	
	public Object handler(T entry);
	
}
