#### description
example in this package:  
&emsp;&emsp; UseConditionWaitNotifyError
	 
*** 
RunTest.javaִ�н����
```
Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
	at java.util.concurrent.locks.ReentrantLock$Sync.tryRelease(Unknown Source)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.release(Unknown Source)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.fullyRelease(Unknown Source)
	at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(Unknown Source)
	at com.chapter4.P205.MyService.await(MyService.java:13)
	at com.chapter4.P205.ThreadA.run(ThreadA.java:13)
```
**ԭ��**    
&emsp;&emsp;����������������wait()���������������ڲ�ʹ�ã��������condition.await()����֮ǰ�������lock.lock()���ͬ��������



