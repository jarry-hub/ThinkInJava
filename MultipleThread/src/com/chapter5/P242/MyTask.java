package com.chapter5.P242;

import java.util.Date;
import java.util.TimerTask;

public class MyTask extends TimerTask {
	
	@Override
	public void run() {
		System.out.println("����ִ���ˣ�ʱ��Ϊ��" + System.currentTimeMillis() + ", " + new Date());
		try {
			Thread.sleep(10000L);	//RunTest5.javaʹ��
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
