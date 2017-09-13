#### description
example in this package:  
- scheduld(TimerTask task, Date time): 在指定的日期执行一次某一任务。

*** 
RunTest.java执行结果：
```
当前时间为：1504529542114
任务执行了，时间为：1504529552163
```
**说明**
&emsp;&emsp;定时任务，10s后执行了。注意：任务虽然执行完了，但进程还未销毁，why?????????
**分析**
&emsp;&emsp;创建Timer对象时：
```
public Timer() {
		this("Timer-" + serialNumber());
	}
//调用下面的函数
public Timer(String arg0) {
		this.queue = new TaskQueue();
		this.thread = new TimerThread(this.queue);
		this.threadReaper = new Object() {
			protected void finalize() throws Throwable {
				synchronized (Timer.this.queue) {
					Timer.this.thread.newTasksMayBeScheduled = false;
					Timer.this.queue.notify();
				}
			}
		};
		this.thread.setName(arg0);
		this.thread.start();
	}
```
创建一个Timer就是启动一个新的线程，这个线程并不是守护线程，会一直运行。
***