package com.chapter4.P208;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void awaitA() {
		try {
			lock.lock();
			System.out.println("begin awaitA ʱ��Ϊ: " + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
			condition.await();
			System.out.println(" end  awaitA ʱ��Ϊ: " + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void awaitB() {
		try {
			lock.lock();
			System.out.println("begin awaitB ʱ��Ϊ: " + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
			condition.await();
			System.out.println(" end  awaitB ʱ��Ϊ: " + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signalAll() {
		try {
			lock.lock();
			System.out.println(" signalAll   ʱ��Ϊ��" + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

}
