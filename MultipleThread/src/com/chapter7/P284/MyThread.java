package com.chapter7.P284;

public class MyThread extends Thread {
	
	@Override
	public void run() {
		try {
			synchronized(this) {
				this.wait();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
