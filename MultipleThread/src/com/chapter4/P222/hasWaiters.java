package com.chapter4.P222;

public class hasWaiters {
	
	public static void main(String[] args) throws InterruptedException {
		MyService myService = new MyService();
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				myService.conditionWatiMehod();
			}
		};
		
		Thread[] threadArray = new Thread[10];
		for(int i=0; i<10; i++) {
			threadArray[i] = new Thread(runnable);
		}
		//��˳����������
		for(int i=0; i<10; i++) {
			threadArray[i].start();
		}
		
		Thread.sleep(2000);
		myService.notityMehtod();
	}

}
