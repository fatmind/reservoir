package com.fatmind.reservoir.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 流控入口 . //XXX add 关联流控
 * @author fatmind
 */
public class FlowEntry {
	
	/**
	 * 具体单个流控点唯一标识		//TODO 考虑：默认规则 + 用户输入
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
	private FlowPostHandler handler = new DefaultFlowPostHandler();
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
	public FlowPostHandler getHandler() {
		return handler;
	}
	public void setHandler(FlowPostHandler handler) {
		this.handler = handler;
	}
	public FlowStrategy getFlowStrategy() {
		return flowStrategy;
	}
	public void setFlowStrategy(FlowStrategy flowStrategy) {
		this.flowStrategy = flowStrategy;
	} 
	
}
