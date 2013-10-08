package com.fatmind.reservoir.flow;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ������� 
 * @author fatmind
 */
public class FlowEntry {
	
	/**
	 * ���嵥�����ص�Ψһ��ʶ		//TODO ���ǣ�Ĭ�Ϲ��� + �û�����
	 */
	private String key;
	/**
	 * ��ֵ
	 */
	private String threshold;
	/**
	 * ������
	 */
	private AtomicInteger counter;
	/**
	 * ���ش�����
	 */
	private FlowSolver flowSolver;
	/**
	 * ������ڣ���ͬ�����Ƿ�����
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
