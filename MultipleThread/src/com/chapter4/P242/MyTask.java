package com.chapter4.P242;

import java.util.TimerTask;

public class MyTask extends TimerTask {
	
	@Override
	public void run() {
		System.out.println("����ִ���ˣ�ʱ��Ϊ��" + System.currentTimeMillis());
	}

}
