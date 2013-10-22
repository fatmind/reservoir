package com.fatmind.reservoir.degrade;

public class DegradeEntry {
	
	/**
	 * 具体服务的标识
	 */
	private String key;
	
	/**
	 * 是否关闭此服务
	 */
	private boolean off = false;
	
	/**
	 * rt阈值，单位毫秒，默认值3000
	 */
	private int rt = 3000;
	
	/**
	 * 失败率阈值，默认值0.8
	 */
	private double failureRate = 0.8;
	
	/**
	 * 服务降级后置处理器
	 */
	private DegradePostHandler postHandler;
	
	/**
	 * 是否降级
	 */
	private boolean degrade = false;
	
	
	
	public boolean isDegrade() {
		return degrade;
	}
	public void setDegrade(boolean degrade) {
		this.degrade = degrade;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isOff() {
		return off;
	}
	public void setOff(boolean off) {
		this.off = off;
	}
	public int getRt() {
		return rt;
	}
	public void setRt(int rt) {
		this.rt = rt;
	}
	public double getFailureRate() {
		return failureRate;
	}
	public void setFailureRate(double failureRate) {
		this.failureRate = failureRate;
	}
	public DegradePostHandler getPostHandler() {
		return postHandler;
	}
	public void setPostHandler(DegradePostHandler postHandler) {
		this.postHandler = postHandler;
	}
	
}
