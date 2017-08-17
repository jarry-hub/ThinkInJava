package com.chapter3.P187;

/**
 * ʹ��sleep�ȴ��߳̽���
 * @author junjie.chang
 *
 */
public class ThreadA extends Thread {
	
	private ThreadB b;
	public ThreadA(ThreadB b) {
		super();
		this.b = b;
	}
	
	@Override
	public void run() {
		try{
			synchronized(b) {
				System.out.println("A run begin." +Thread.currentThread().getName() +", time= " + System.currentTimeMillis());
				Thread.sleep(5000);
				System.out.println("A run end.  " +Thread.currentThread().getName() +", time= " + System.currentTimeMillis());
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
