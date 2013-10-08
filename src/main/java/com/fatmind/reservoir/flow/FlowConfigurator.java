package com.fatmind.reservoir.flow;

import java.util.List;
import java.util.Map;

/**
 * ����������
 * @author fatmind
 */
public interface FlowConfigurator {
	
	/**
	 * ��ʼ�������������
	 * @return Map<String, FlowEntry>
	 * @throws Exception
	 */
	public Map<String, FlowEntry> initFlowEntry() throws Exception;
	
	/**
	 * 
	 * @return
	 */
	public List<String> getFlowBlackName();
	
}
