#### description
example in this package:  
&emsp;&emsp; UseConditionWaitNotifyOK
	 
*** 
RunTest.javaִ�н����
```
await  ʱ��Ϊ��1503489908210
signal ʱ��Ϊ��1503489909209
```
**�ɹ�**    
```
//��Ӧ��ϵ
object(wait) <--> condition(await); 
object(wait(long timeout)) <--> condition(long time, TimeUnit unit); 
object(notify) <--> condition(signal); 
object(notifyAll) <--> condition(signalAll);
```



