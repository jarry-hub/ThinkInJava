package com.chapter4.P242;

import java.util.TimerTask;

public class MyTask extends TimerTask {
	
	@Override
	public void run() {
		System.out.println("任务执行了，时间为：" + System.currentTimeMillis());
	}

}
