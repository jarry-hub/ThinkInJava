package com.chapter4.P216;

public class RunFair{
	
	private MyService myService = new MyService(true);
	
	public static void main(String[] args) {
		MyService myService = new MyService(true);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("ThreadName=" + Thread.currentThread().getName() + " start. ");
				myService.serviceMethod();
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
		
	}

}
