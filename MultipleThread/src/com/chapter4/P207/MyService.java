package com.chapter4.P207;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void await() {
		try {
			lock.lock();
			System.out.println("await  ʱ��Ϊ��" + System.currentTimeMillis());
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signal() {
		try {
			lock.lock();
			System.out.println("signal ʱ��Ϊ��" + System.currentTimeMillis());
			condition.signal();
		} finally {
			lock.unlock();
		}
	}

}
