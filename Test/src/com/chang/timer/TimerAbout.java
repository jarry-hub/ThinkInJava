package com.chang.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class MyTask extends TimerTask{
	
    @Override
    public void run() {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
    	System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
        System.out.println("��������!!!!");
    }
}

public class TimerAbout {
    public static void main(String[] args) {
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
    	System.out.println(df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
    	
        //������ʱ������
        Timer t=new Timer();
        //��3���ִ��MyTask���е�run����
        MyTask task = new MyTask();
        t.schedule(task, 3000, 3000); //����  ����, delay, period
        
        System.out.println(task.getClass().getName());
    }

}
