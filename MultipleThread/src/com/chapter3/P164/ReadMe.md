#### description
example in this package:  
&emsp;&emsp;һ������һ���ѣ�����ջ
&emsp;&emsp;���մ����߼��������쳣
	 
*** 
 ������ʵ���������Գ�������(����ʱ)  
 �������£�
```
	pop �����еģ�Thread-1 �̳߳�waitװ״̬. 
	push = 1
	push �����еģ�Thread-0 �̳߳�waitװ״̬. 
	Exception in thread "Thread-1" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
		at java.util.ArrayList.rangeCheck(Unknown Source)
		at java.util.ArrayList.get(Unknown Source)
		at com.multi.thread.P164.MyStack.pop(MyStack.java:34)
		at com.multi.thread.P164.Consumer.popService(Consumer.java:13)
		at com.multi.thread.P164.ConsumerThread.run(ConsumerThread.java:15)
```
