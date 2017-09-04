package com.chapter4.P237;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyService {
	
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println("���д����" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(10000);
			} finally {
				lock.writeLock().unlock();
			}
		} catch( InterruptedException e) {
			e.printStackTrace();
		}
	}

}
