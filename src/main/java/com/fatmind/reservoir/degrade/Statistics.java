package com.fatmind.reservoir.degrade;

public class Statistics {
	/**
	 * 失败率
	 */
	private double failRate;
	/**
	 * 平均RT
	 */
	private long avgRt;
	
	
	public double getFailRate() {
		return failRate;
	}
	public void setFailRate(double failRate) {
		this.failRate = failRate;
	}
	public long getAvgRt() {
		return avgRt;
	}
	public void setAvgRt(long avgRt) {
		this.avgRt = avgRt;
	}
	
}
