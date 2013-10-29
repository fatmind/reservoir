package com.fatmind.reservoir.example.flow;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext app = new ClassPathXmlApplicationContext("flow_example.xml");
		final ItemService itemService = (ItemService)app.getBean("itemService");
		
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, queue);
		
		for(int i=0; i<20; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					itemService.queryItemById(0);
				}
			});
		}
	}

}
