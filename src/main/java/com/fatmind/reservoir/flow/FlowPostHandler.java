package com.fatmind.reservoir.flow;

/**
 * ���غ��ô�����. ��ʹ���߶��屻���غ���δ���Ĭ�Ϸ���Null
 * @author fatmind
 */
public interface FlowPostHandler {
	
	public Object handler(FlowEntry flowEntry);
	
}
