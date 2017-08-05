/**
 * ���� return i ��ԭ���Բ���������ȱ��ͬ��ʹ������ֵ�����ڴ��ڲ��ȶ����м�״̬ʱ����ȡ��
 * ����֮�⣬����iҲ����volatile�ģ����ڿ��������⡣
 */

package com.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class P682AtomicityTest implements Runnable {
	private int i = 0;
	public int getValue() {	//������synchronized��
		return i;
	}
	
	private synchronized void evenIncrement() {
		i++;
		i++;
	}
	
	public void run() {
		while(true) {
			evenIncrement();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		P682AtomicityTest at = new P682AtomicityTest();
		exec.execute(at);
		while(true) {
			int val = at.getValue();
			if( 0 != val%2) {
				System.out.println(val);
				System.exit(0);
			}
		}
		
	}
	

}
