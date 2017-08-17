package com.chapter3.P184;

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
				b.start();
				Thread.sleep(6000);	//���ͷ���
			}
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

}
