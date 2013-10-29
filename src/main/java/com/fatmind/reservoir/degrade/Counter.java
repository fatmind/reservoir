package com.fatmind.reservoir.degrade;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

public class Counter {

	/**
	 * 请求次数
	 */
	private AtomicLong req;
	
	/**
	 * 响应时间
	 */
	private AtomicLong rt;
	
	/**
	 * 失败次数
	 */
	private AtomicLong fail;

	
	/**
	 * 累积容量
	 */
	private static final int CPACITY = 6;
	
	/**
	 * 30分钟统计数据
	 */
	private Queue<Statistics> thirtyMin = new ArrayDeque<Statistics>(CPACITY);
	
	
	/**
	 * 计算一次平均rt和失败率
	 */
	public void calculate() {
		
		if(req.get() == 0) return;	// 若req为0，意味无请求，则不计算	
		
		Statistics statistics = new Statistics();
		statistics.setAvgRt(rt.get() / req.get());
		statistics.setFailRate(fail.get() / req.get());
		
		if(thirtyMin.size() == CPACITY) thirtyMin.poll();
		thirtyMin.add(statistics);
		
		reset(); 	// 每统计一次后，清空当前计数
	}
	
	/**
	 * 30分钟累计统计数据. 若未累计30分钟，则返回Null
	 * @return Statistics
	 */
	public Statistics thirtyMinStatistics() {
		
		if(thirtyMin.size() < CPACITY) return null;
		
		long thirtyAvgRt = 0;
		double thirtyFailRate = 0;
		for(Statistics s : thirtyMin) {
			thirtyAvgRt = (thirtyAvgRt + s.getAvgRt()) / 2;
			thirtyFailRate = (thirtyFailRate + s.getFailRate()) / 2;
		}
		Statistics statistics = new Statistics();
		statistics.setAvgRt(thirtyAvgRt);
		statistics.setFailRate(thirtyFailRate);
		return statistics;
	}
	
	/**
	 * 清除累计值
	 */
	public void reset() {
		req.set(0);
		rt.set(0);
		fail.set(0);
	}
	
	
	public AtomicLong getReq() {
		return req;
	}

	public void setReq(AtomicLong req) {
		this.req = req;
	}

	public AtomicLong getRt() {
		return rt;
	}

	public void setRt(AtomicLong rt) {
		this.rt = rt;
	}

	public AtomicLong getFail() {
		return fail;
	}

	public void setFail(AtomicLong fail) {
		this.fail = fail;
	}

	class Statistics {
		
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
	
}
