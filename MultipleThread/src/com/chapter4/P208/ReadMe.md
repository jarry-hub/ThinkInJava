#### description
example in this package:  
&emsp;&emsp; MustUseMoreCondition_Error
	 
*** 
RunTest.javaִ�н����
```
begin awaitA ʱ��Ϊ: 1503490678067, ThreadName=Thread-0
begin awaitB ʱ��Ϊ: 1503490678067, ThreadName=Thread-1
 signalAll   ʱ��Ϊ��1503490680067, ThreadName=main
 end  awaitA ʱ��Ϊ: 1503490680067, ThreadName=Thread-0
 end  awaitB ʱ��Ϊ: 1503490680067, ThreadName=Thread-1
```
&emsp;&emsp;�������ûʲô���ԣ���Ҫ֪ͨ����ֻ��lock�����Ҵ���await״̬�ľ�ʹ��signalAll, ���ʵ��֪ͨ�����ض����̣߳��鿴����һ����Ŀ��



