package com.fatmind.reservoir.degrade;

public class TrackDO {

	/**
	 * 响应时间
	 */
	private long rt;
	/**
	 * 是否成功
	 */
	private boolean success;

	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public long getRt() {
		return rt;
	}
	public void setRt(long rt) {
		this.rt = rt;
	}
	
}
