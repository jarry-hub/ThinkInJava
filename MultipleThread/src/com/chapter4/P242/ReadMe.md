#### description
example in this package:  
- scheduld(TimerTask task, Date time): ��ָ��������ִ��һ��ĳһ����

*** 
RunTest.javaִ�н����
```
��ǰʱ��Ϊ��1504529542114
����ִ���ˣ�ʱ��Ϊ��1504529552163
```
**˵��**
&emsp;&emsp;��ʱ����10s��ִ���ˡ�ע�⣺������Ȼִ�����ˣ������̻�δ���٣�why?????????
**����**
&emsp;&emsp;����Timer����ʱ��
```
public Timer() {
		this("Timer-" + serialNumber());
	}
//��������ĺ���
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
����һ��Timer��������һ���µ��̣߳�����̲߳������ػ��̣߳���һֱ���С�
***