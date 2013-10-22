package com.fatmind.reservoir.degrade;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 服务rt/failrate统计器
 * @author bohan.sj
 */
public class Statistics {
	
	Map<DegradeEntry, Stack<TrackDO>> store = new ConcurrentHashMap<DegradeEntry, Stack<TrackDO>>();
	
	//TODO 如何判断scheduler是否已经挂掉  ?
	ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	
	public void init(List<DegradeEntry> degradeEntries) {
		
		for(DegradeEntry e : degradeEntries) {
			Stack<TrackDO> stack = new Stack<TrackDO>();
			store.put(e, stack);
		}
		scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				for(Entry<DegradeEntry, Stack<TrackDO>> e : store.entrySet()) {	//遍历时，有其它的修改 ?
				}
			}
		}, 10, 5, TimeUnit.MINUTES);
	}
	
	
	public void addTrack(DegradeEntry degradeEntry, TrackDO trackDO) {
		Stack<TrackDO> stack = store.get(degradeEntry);
		stack.add(trackDO); 
	}
	
}
