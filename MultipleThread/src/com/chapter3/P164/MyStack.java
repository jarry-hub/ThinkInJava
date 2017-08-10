/**
 * һ������һ���ѣ�����ջ
 */
package com.chapter3.P164;

import java.util.ArrayList;
import java.util.List;

public class MyStack {
	
	private List list = new ArrayList();
	
	synchronized public void push() {
		try{
			if( 1 == list.size()) {
				System.out.println("push �����еģ�" + Thread.currentThread().getName() + " �̳߳�waitװ״̬. ");
				this.wait();
			}
			list.add("anyString = " + Math.random());
			this.notify();
			System.out.println("push = " + list.size());
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public String pop() {
		String returnValue = "";
		try {
			if(0 == list.size()) {
				System.out.println("pop �����еģ�" + Thread.currentThread().getName() + " �̳߳�waitװ״̬. ");
				this.wait();
			}
			returnValue = "" + list.get(0);
			list.remove(0);
			this.notify();
			System.out.println("pop = " + list.size());
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		return returnValue;
	}

}
 