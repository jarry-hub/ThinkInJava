package com.chapter4.P202;

public class ThreadA extends Thread {
		
	private MyService myService;
		
	public ThreadA(MyService service) {
		this.myService = service;
	}
	
	@Override
	public void run() {
		myService.methodA();
	}

}
