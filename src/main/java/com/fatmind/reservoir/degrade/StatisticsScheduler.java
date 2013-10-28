package com.fatmind.reservoir.degrade;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fatmind.reservoir.Configurator;


/**
 * 统计定时器
 * @author bohan.sj
 */
public class StatisticsScheduler {
	
	private Logger log = LoggerFactory.getLogger(StatisticsScheduler.class);
	
	private Configurator<DegradeEntry> configurator;
	
	/**
	 * 定时执行，计算平均rt与失败率
	 */
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); //TODO 如何判断scheduler是否已经挂掉  ?
	
	
	public StatisticsScheduler(Configurator<DegradeEntry> configurator) {
		this.configurator = configurator;
		init();
	}
	
	private void init() {
		
		scheduler.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				
				List<DegradeEntry> entries = configurator.getEntries();
				if(entries == null || entries.size() == 0) return;
				
				for(DegradeEntry e : entries) {	//TODO 遍历时，有其它的修改，如add，会导致ConcurrentModificationException   ？
					
					Counter counter = e.getCounter();
					counter.calculate();
					counter.reset();	// 每统计一次后，清空当前计数
					
					Statistics statistics = counter.thirtyMinStatistics();
					if(e.getRt() < statistics.getAvgRt() || e.getFailureRate() < statistics.getFailRate()) {
						e.setDegrade(true);
						log.info(e.getKey() + " is degraded, avgRt = " + statistics.getAvgRt() + ", failRate = " + statistics.getFailRate());
					}
				}
			}
		}, 10, 5, TimeUnit.MINUTES);
	}
	
}
