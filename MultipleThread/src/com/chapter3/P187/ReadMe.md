#### description
example in this package:  
&emsp;&emsp; ����join()����Ĵ�����ǰ���У���������
	 
*** 
ԭ��  
&emsp;&emsp;��RunTest2�Ľ��������main end������ִ�У�˵��RunTest1��main�߳��е�b.join()����������b��������ִ�й��̿������£�
1. b.join(2000)���b���������ͷ�b��������
2. �߳�a���b��������ִ��ֱ���������ͷ�b��������
3. **�߳�b��main�߳�����b������**�� ��b�̻߳����������������
	```
	A run begin.Thread-1, time= 1502951581603
	A run end.  Thread-1, time= 1502951586602
	B run begin.Thread-0, time= 1502951586602
	B run end.  Thread-0, time= 1502951591602
	main continue b.join(2000),main, time= 1502951591602
	```
	��main�����,����ʱ���ѹ����ͷ�������
	```
	A run begin.Thread-1, time= 1502951523200
	A run end.  Thread-1, time= 1502951528200
	main continue b.join(2000),main, time= 1502951528200
	B run begin.Thread-0, time= 1502951528200
	B run end.  Thread-0, time= 1502951533200
	```
  
**���ܳ��ֵ��������**
&emsp;&emsp;�����߳��������߳�b�����(���ͷ���)�������߳�b.join(2000)֮��Ĵ������߳�b���У����ܳ��������ִ��˳��
```
A run begin.
A run end.
B run begin.
main continue b.join(2000),main
B run end.
```

��ע��
1. �̵߳�join()��������ͬ��������
2. �̵߳�join(long time)��ͬ����������ִ��ǰ���ö������������ж�joinʱ���Ƿ�ʱʱҲҪ��ö���������Ҳ˵�����̵߳ȴ���ʱ�䲻���ϸ��join�����Ĳ���time��