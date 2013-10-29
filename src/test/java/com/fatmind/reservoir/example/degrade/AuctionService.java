package com.fatmind.reservoir.example.degrade;

import com.fatmind.reservoir.degrade.Degrade;

public class AuctionService {
	
	private int count;
	
	@Degrade
	public Object queryAuctionById(long auctionId) {
		System.out.println(Thread.currentThread().getName() + " - query item by id ... id = " + auctionId);
		try {
			if(count > 10) {
				Thread.sleep(1000);
			} else {
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.MAX_VALUE;
	}
	
}
