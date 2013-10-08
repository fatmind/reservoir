package com.fatmind.reservoir.flow;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 流控入口 
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
	private String threshold;
	/**
	 * 计数器
	 */
	private AtomicInteger counter;
	/**
	 * 流控处理器
	 */
	private FlowSolver flowSolver;
	/**
	 * 关联入口，共同决定是否流控
	 */
	private List<FlowEntry> relationEntry;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	public AtomicInteger getCounter() {
		return counter;
	}
	public void setCounter(AtomicInteger counter) {
		this.counter = counter;
	}
	public FlowSolver getFlowSolver() {
		return flowSolver;
	}
	public void setFlowSolver(FlowSolver flowSolver) {
		this.flowSolver = flowSolver;
	}
	public List<FlowEntry> getRelationEntry() {
		return relationEntry;
	}
	public void setRelationEntry(List<FlowEntry> relationEntry) {
		this.relationEntry = relationEntry;
	}
	
	
	
}
