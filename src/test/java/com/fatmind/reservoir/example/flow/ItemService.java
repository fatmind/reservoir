package com.fatmind.reservoir.example.flow;

import com.fatmind.reservoir.flow.Flow;

public class ItemService {
	
	@Flow
	public void queryItemById(long itemId) {
		System.out.println(Thread.currentThread().getName() + " - query item by id ... id = " + itemId);
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
