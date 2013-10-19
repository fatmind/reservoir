package com.fatmind.reservoir.flow;

/**
 * ���ز���
 * @author fatmind
 */
public interface FlowStrategy {

	/**
	 * �Ƿ���Ҫ����
	 * @param flowEntry
	 * @param from ��Դ��ʶ
	 * @return boolean
	 */
	public boolean isNeedBlocked(FlowEntry flowEntry, String from);
	
}
