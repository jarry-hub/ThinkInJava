package com.chapter4.P214.notifyAll;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService {
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	private boolean hasValue = false;
	
	public void set() {
		try {
			lock.lock();
			while(true == hasValue) {
				System.out.println("set before await:¡î¡î");
				condition.await();
			}
			System.out.println("¡î¡î¡î¡î¡î");
			hasValue = true;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void get() {
		try {
			lock.lock();
			while(false == hasValue) {
				System.out.println("get before await:¡ï¡ï");
				condition.await();
			}
			System.out.println("¡ï¡ï¡ï¡ï¡ï");
			hasValue = false;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
