package com.chang.thread.pool;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPoolExecutor {
	
	volatile int runState;
	static final int RUNNING    = 0;
	static final int SHUTDOWN   = 1;
	static final int STOP       = 2;
	static final int TERMINATED = 3;
	
	
	private final BlockingQueue<Runnable> workQueue;              //���񻺴���У�������ŵȴ�ִ�е�����
	private final ReentrantLock mainLock = new ReentrantLock();   //�̳߳ص���Ҫ״̬�������̳߳�״̬�������̳߳ش�С
	                                                              //��runState�ȣ��ĸı䶼Ҫʹ�������
	private final HashSet<Worker> workers = new HashSet<Worker>();  //������Ź�����
	 
	private volatile long  keepAliveTime;    //�̴߳��ʱ��   
	private volatile boolean allowCoreThreadTimeOut;   //�Ƿ�����Ϊ�����߳����ô��ʱ��
	private volatile int   corePoolSize;     //���ĳصĴ�С�����̳߳��е��߳���Ŀ�����������ʱ���ύ������ᱻ�Ž����񻺴���У�
	private volatile int   maximumPoolSize;   //�̳߳���������̵��߳���
	 
	private volatile int   poolSize;       //�̳߳��е�ǰ���߳���
	 
	private volatile RejectedExecutionHandler handler; //����ܾ�����
	 
	private volatile ThreadFactory threadFactory;   //�̹߳��������������߳�
	 
	private int largestPoolSize;   //������¼�̳߳����������ֹ�������߳���
	 
	private long completedTaskCount;   //������¼�Ѿ�ִ����ϵ��������
	
	public MyThreadPoolExecutor(int corePoolSize,
        int maximumPoolSize,
        long keepAliveTime,
        TimeUnit unit,
        BlockingQueue<Runnable> workQueue,
        ThreadFactory threadFactory,
        RejectedExecutionHandler handler) {
			if (corePoolSize < 0 ||
			maximumPoolSize <= 0 ||
			maximumPoolSize < corePoolSize ||
			keepAliveTime < 0)
			throw new IllegalArgumentException();
			if (workQueue == null || threadFactory == null || handler == null)
			throw new NullPointerException();
			this.corePoolSize = corePoolSize;
			this.maximumPoolSize = maximumPoolSize;
			this.workQueue = workQueue;
			this.keepAliveTime = unit.toNanos(keepAliveTime);
			this.threadFactory = threadFactory;
			this.handler = handler;
		}
	
	public void execute(Runnable command) {
	    if (command == null)
	        throw new NullPointerException();
	    if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {
	        if (runState == RUNNING && workQueue.offer(command)) {
	            if (runState != RUNNING || poolSize == 0) {
	                //ensureQueuedTaskHandled(command);
	            }
	        }
	        else if (!addIfUnderMaximumPoolSize(command)) {
	            //reject(command); // is shutdown or saturated
	        }
	    }
	}
	
	private boolean addIfUnderCorePoolSize(Runnable firstTask) {
	    Thread t = null;
	    final ReentrantLock mainLock = this.mainLock;
	    mainLock.lock();
	    try {
	        if (poolSize < corePoolSize && runState == RUNNING)
	            t = addThread(firstTask);        //�����߳�ȥִ��firstTask����   
	        } finally {
	        mainLock.unlock();
	    }
	    if (t == null)
	        return false;
	    t.start();
	    return true;
	}
	
	private boolean addIfUnderMaximumPoolSize(Runnable firstTask) {
	    Thread t = null;
	    final ReentrantLock mainLock = this.mainLock;
	    mainLock.lock();
	    try {
	        if (poolSize < maximumPoolSize && runState == RUNNING)
	            t = addThread(firstTask);
	    } finally {
	        mainLock.unlock();
	    }
	    if (t == null)
	        return false;
	    t.start();
	    return true;
	}
	
	private Thread addThread(Runnable firstTask) {
	    Worker w = new Worker(firstTask);
	    Thread t = threadFactory.newThread(w);  //����һ���̣߳�ִ������   
	    if (t != null) {
	        w.thread = t;            //���������̵߳����ø�ֵΪw�ĳ�Ա����       
	        workers.add(w);
	        int nt = ++poolSize;     //��ǰ�߳�����1       
	        if (nt > largestPoolSize)
	            largestPoolSize = nt;
	    }
	    return t;
	}
	
	Runnable getTask() {
	    for (;;) {
	        try {
	            int state = runState;
	            if (state > SHUTDOWN)
	                return null;
	            Runnable r;
	            if (state == SHUTDOWN)  // Help drain queue
	                r = workQueue.poll();
	            else if (poolSize > corePoolSize || allowCoreThreadTimeOut) //����߳������ں��ĳش�С��������Ϊ���ĳ��߳����ÿ���ʱ�䣬
	                //��ͨ��pollȡ�������ȴ�һ����ʱ��ȡ���������򷵻�null
	                r = workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS);
	            else
	                r = workQueue.take();
	            if (r != null)
	                return r;
	            if (workerCanExit()) {    //���ûȡ�����񣬼�rΪnull�����жϵ�ǰ��worker�Ƿ�����˳�
	                if (runState >= SHUTDOWN) // Wake up others
	                    interruptIdleWorkers();   //�жϴ��ڿ���״̬��worker
	                return null;
	            }
	            // Else retry
	        } catch (InterruptedException ie) {
	            // On interruption, re-check runState
	        }
	    }
	}
	
	private boolean workerCanExit() {
	    final ReentrantLock mainLock = this.mainLock;
	    mainLock.lock();
	    boolean canExit;
	    //���runState���ڵ���STOP���������񻺴����Ϊ����
	    //����  ����Ϊ���ĳ��߳����ÿ��д��ʱ�䲢���̳߳��е��߳���Ŀ����1
	    try {
	        canExit = runState >= STOP ||
	            workQueue.isEmpty() ||
	            (allowCoreThreadTimeOut &&
	             poolSize > Math.max(1, corePoolSize));
	    } finally {
	        mainLock.unlock();
	    }
	    return canExit;
	}
	
	void interruptIdleWorkers() {
	    final ReentrantLock mainLock = this.mainLock;
	    mainLock.lock();
	    try {
	        for (Worker w : workers)  //ʵ���ϵ��õ���worker��interruptIfIdle()����
	            w.interruptIfIdle();
	    } finally {
	        mainLock.unlock();
	    }
	}
	
	
	/**************************************** �̳߳��е��̳߳�ʼ�� **************************************************/
	public boolean prestartCoreThread() {
	    return addIfUnderCorePoolSize(null); //ע�⴫��ȥ�Ĳ�����null
	}
	 
	public int prestartAllCoreThreads() {
	    int n = 0;
	    while (addIfUnderCorePoolSize(null))//ע�⴫��ȥ�Ĳ�����null
	        ++n;
	    return n;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private final class Worker implements Runnable {
	    private final ReentrantLock runLock = new ReentrantLock();
	    private Runnable firstTask;
	    volatile long completedTasks;
	    Thread thread;
	    Worker(Runnable firstTask) {
	        this.firstTask = firstTask;
	    }
	    boolean isActive() {
	        return runLock.isLocked();
	    }
	    void interruptIfIdle() {
	        final ReentrantLock runLock = this.runLock;
	        if (runLock.tryLock()) {
	            try {
	        if (thread != Thread.currentThread())
	        thread.interrupt();
	            } finally {
	                runLock.unlock();
	            }
	        }
	    }
	    void interruptNow() {
	        thread.interrupt();
	    }
	 
	    private void runTask(Runnable task) {
	        final ReentrantLock runLock = this.runLock;
	        runLock.lock();
	        boolean ran = false;
	        try {
	            if (runState < STOP && Thread.interrupted() && runState >= STOP)
	            	ran = false;
	            //beforeExecute(thread, task);   //beforeExecute������ThreadPoolExecutor���һ��������û�о���ʵ�֣��û����Ը���
	            //�Լ���Ҫ������������ͺ����afterExecute����������һЩͳ����Ϣ������ĳ�������ִ��ʱ���           
	            try {
	                task.run();
	                ran = true;
	                //afterExecute(task, null);
	                ++completedTasks;
	            } catch (RuntimeException ex) {
	                if (!ran)
	                    //afterExecute(task, ex);
	                throw ex;
	            }
	        } finally {
	            runLock.unlock();
	        }
	    }
	 
	    public void run() {
	        try {
	            Runnable task = firstTask;
	            firstTask = null;
	            while (task != null || (task = getTask()) != null) {
	                runTask(task);
	                task = null;
	            }
	        } finally {
	            //workerDone(this);   //�����������û������ʱ������������       
	        }
	    }
	}

}



