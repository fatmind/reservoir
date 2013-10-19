package com.fatmind.reservoir.flow;

/**
 * Ĭ�����ز���. 1.���ں�����������    2.������ֵ������
 * @author fatmind
 */
public class DefaultFlowStrategy implements FlowStrategy {

	@Override
	public boolean isNeedBlocked(FlowEntry flowEntry, String from) {
		
		/*
		 * �����ں���������Ҫ����
		 */
		if(flowEntry.getBlackNames() != null 
				&& flowEntry.getBlackNames().contains(from)) {
			return true;
		}
		
		/*
		 * ���ۼƴ�����������������ֵ����Ҫ����
		 */
		if(flowEntry.getCounter().get() > flowEntry.getThreshold()) {
			return true;
		}
		
		return false;
	}
	
	

}
