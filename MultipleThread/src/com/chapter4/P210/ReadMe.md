#### description
example in this package:  
&emsp;&emsp; MustUseMoreCondition_OK
	 
*** 
RunTest.javaִ�н����
```
begin awaitA ʱ��Ϊ: 1503491657317, ThreadName=Thread-0
begin awaitB ʱ��Ϊ: 1503491657318, ThreadName=Thread-1
begin awaitB ʱ��Ϊ: 1503491657318, ThreadName=Thread-2
 signalA     ʱ��Ϊ��1503491659317, ThreadName=main
 end  awaitA ʱ��Ϊ: 1503491659317, ThreadName=Thread-0
 signalBAll  ʱ��Ϊ��1503491661317, ThreadName=main
 end  awaitB ʱ��Ϊ: 1503491661317, ThreadName=Thread-1
 end  awaitB ʱ��Ϊ: 1503491661317, ThreadName=Thread-2
```
**����**
&emsp;&emsp;ͬһ��Lock�¿��ж��Condition,�߳̿���ʹ�ò�ͬ��Condition����await,���Ӧ��Condition����signal���л���(ʹ��signalAll�������Ի��ѵ��ø�Condition����await�������߳�)
&emsp;&emsp;ʹ��ReentrantLock������ָ������߳�



