package com.fatmind.reservoir.flow;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ������� . //XXX add ��������
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
	private int threshold;
	/**
	 * ������
	 */
	private List<String> blackNames = new ArrayList<String>();
	/**
	 * ������
	 */
	private AtomicInteger counter = new AtomicInteger(0);
	/**
	 * ������. Ĭ�ϴ���������Null
	 */
	private FlowPostHandler handler = new DefaultFlowPostHandler();
	/**
	 * ���ز���.
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
