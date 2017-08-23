package com.chapter4.P210;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	
	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();
	
	public void awaitA() {
		try {
			lock.lock();
			System.out.println("begin awaitA ʱ��Ϊ: " + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
			conditionA.await();
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
			conditionB.await();
			System.out.println(" end  awaitB ʱ��Ϊ: " + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signalA() {
		try {
			lock.lock();
			System.out.println(" signalA     ʱ��Ϊ��" + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
			conditionA.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void signalBAll() {
		try {
			lock.lock();
			System.out.println(" signalBAll  ʱ��Ϊ��" + System.currentTimeMillis() + ", ThreadName=" + Thread.currentThread().getName());
			conditionB.signalAll();
		} finally {
			lock.unlock();
		}
	}

}
