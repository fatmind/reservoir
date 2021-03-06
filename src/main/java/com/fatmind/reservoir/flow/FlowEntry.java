package com.fatmind.reservoir.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.fatmind.reservoir.PostHandler;

/**
 * 流控入口 .
 * @author fatmind
 */
public class FlowEntry {
	
	/**
	 * 具体单个流控点唯一标识	
	 */
	private String key;
	/**
	 * 阈值
	 */
	private int threshold;
	/**
	 * 黑名单
	 */
	private List<String> blackNames = new ArrayList<String>();
	/**
	 * 计数器
	 */
	private AtomicInteger counter = new AtomicInteger(0);
	/**
	 * 处理器. 默认处理器返回Null
	 */
	private PostHandler<FlowEntry> handler = new DefaultFlowPostHandler();
	/**
	 * 流控策略.
	 */
	private FlowStrategy flowStrategy = new DefaultFlowStrategy();
	
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public List<String> getBlackNames() {
		return blackNames;
	}
	public void setBlackNames(List<String> blackNames) {
		this.blackNames = blackNames;
	}
	public AtomicInteger getCounter() {
		return counter;
	}
	public PostHandler<FlowEntry> getHandler() {
		return handler;
	}
	public void setHandler(PostHandler<FlowEntry> handler) {
		this.handler = handler;
	}
	public FlowStrategy getFlowStrategy() {
		return flowStrategy;
	}
	public void setFlowStrategy(FlowStrategy flowStrategy) {
		this.flowStrategy = flowStrategy;
	} 
	
}
