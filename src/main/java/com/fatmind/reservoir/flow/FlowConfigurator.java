package com.fatmind.reservoir.flow;


/**
 * ����������
 * @author fatmind
 */
public interface FlowConfigurator {
	
	/**
	 * ����Key��ȡFlowEntry
	 * @param key
	 * @return FlowEntry
	 */
	public FlowEntry getFlowEntry(String key);
	
	/**
	 * ������Դ�ж�, �Ƿ�ܾ�����	 //TODO ����������ȶ
	 * @param from
	 * @return boolean
	 */
	public boolean isRefuse(String from);
	
}
