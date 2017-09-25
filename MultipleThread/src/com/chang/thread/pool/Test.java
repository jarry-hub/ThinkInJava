package com.chang.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.TimeUnit;

public class Test {
	
	private static final RejectedExecutionHandler defaultHandler =
		        new AbortPolicy();
	 
    public static void main(String[] args) {   
    	
    	MyThreadPoolExecutor executor = new MyThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5), Executors.defaultThreadFactory(), defaultHandler);
         
        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            //System.out.println("�̳߳����߳���Ŀ��"+executor.getPoolSize()+"�������еȴ�ִ�е�������Ŀ��"+
            //executor.getQueue().size()+"����ִ������������Ŀ��"+executor.getCompletedTaskCount());
        }
       // executor.shutdown();
    } 
}


class MyTask implements Runnable {
   private int taskNum;
    
   public MyTask(int num) {
       this.taskNum = num;
   }
    
   @Override
   public void run() {
       System.out.println("����ִ��task "+taskNum);
       try {
           Thread.currentThread().sleep(4000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       System.out.println("task "+taskNum+"ִ�����");
   }
}
