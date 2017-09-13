package com.chang.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	
	public static void main(String[] args) {
		ExecutorService fixedThreadpool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {  
			final int index = i;
			fixedThreadpool.execute(new Runnable() {
				public void run() {
					try {
						System.out.println("----------------" + index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});  
		}
		
	}

}