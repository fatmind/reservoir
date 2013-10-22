package com.fatmind.reservoir.degrade;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 统计定时器
 * @author bohan.sj
 */
public class StatisticsScheduler {
	
	/**
	 * 存储DegradeEntry与Counter映射关系
	 */
	private Map<DegradeEntry, Counter> store = new ConcurrentHashMap<DegradeEntry, Counter>();
	/**
	 * 定时执行，计算平均rt与失败率
	 */
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); //TODO 如何判断scheduler是否已经挂掉  ?
	
	
	public void init(List<DegradeEntry> degradeEntries) {
		
		for(DegradeEntry e : degradeEntries) {
			store.put(e, new Counter());
		}
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				
				for(Entry<DegradeEntry, Counter> e : store.entrySet()) {	//TODO 遍历时，有其它的修改 ?
					
					Counter counter = e.getValue();
					counter.calculate();
					counter.reset();
					
					DegradeEntry degradeEntry = e.getKey();
					Statistics statistics = counter.thirtyMinStatistics();
					if(degradeEntry.getRt() < statistics.getAvgRt() || 
							degradeEntry.getFailureRate() < statistics.getFailRate()) {
						degradeEntry.setDegrade(true);
					}
				}
			}
		}, 10, 5, TimeUnit.MINUTES);
	}
	
	/**
	 * 记录单次数据
	 * @param degradeEntry
	 * @param rt
	 * @param execRes
	 */
	public void track(DegradeEntry degradeEntry, long rt, boolean execRes) {
		Counter counter = store.get(degradeEntry);
		counter.getReq().incrementAndGet();
		counter.getRt().addAndGet(rt);
		if(!execRes) counter.getFail().incrementAndGet(); 
	}
	
	/**
	 * 新增或删除entry时，更新对应store
	 * @param degradeEntries
	 */
	public void updateEntryStore(List<DegradeEntry> degradeEntries) {
		
	}
	
}
