#### description  
&emsp;&emsp;每一小结和例子的对应关系
	 
*** 
#### 3.1 等待/通知机制
##### 3.1.1 生产者/消费者模式实现
###### [一生产与一消费:操作栈](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter3/P164)
###### [一生产与多消费:操作栈(ERROR)](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter3/P167)
###### [一生产与多消费:操作栈(OK)](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter3/P168)
###### [多生产与一消费:操作栈](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter3/P169)
###### [多生产与多消费:操作栈](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter3/P170)

#### 3.2 Join方法的使用
##### 3.2.5 [方法join(long)和sleep()的区别](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter3/P184)
##### 3.2.6 [方法join()后面的代码提前运行：出现意外](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter3/P187)

#### 3.3 类ThreadLocal的使用
##### 3.3.1 [方法get()与NULL](com.chapter3.P)


### 第四章 Lock的使用
#### 4.1 使用ReentrantLock类
##### 4.1.1 [使用ReentrantLock实现同步：测试1](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P200)
##### 4.1.2 [使用ReentrantLock实现同步：测试2](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P202)
##### 4.1.3 [使用Condition实现等待/通知:错误用法与解决](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P205)
##### 4.1.4 [正确使用Condition实现等待/通知](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P207)
##### 4.1.5 [使用多个Condition实现通知部分线程：错误用法](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P208)
##### 4.1.6 [使用多个Condition实现通知部分线程：正确用法](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P210)
##### 4.1.7 [实现生产者/消费者模式:一对一交替打印](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P213)
##### 4.1.8 [实现生产者/消费者模式:多对多交替打印](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P214)
##### 4.1.9 [公平锁和非公平锁](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P216)
##### 4.1.10 [方法getHoldCount()/getQueueLength()/getWaitQueueLength()的测试](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P216)
##### 4.1.11 [方法hasQueuedThread()/hasQueuedThreads()/hasWaiters()的测试](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P222)
##### 4.1.12 [方法isFair()/isHeldByCurrentThread()/isLocked()的测试](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P224)
##### 4.1.13 [方法lockInterruptibly()/tryLock()/tryLock(Long timeout, TimeUnit unit)的测试](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P227)
##### 4.1.14 [方法awaitUninterruptibly()的使用](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P230)
##### 4.1.15 [方法awaitUntil()的使用](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P232)
##### 4.1.16 [使用Condition实现顺序执行](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P234)

#### 4.2 使用ReentrantReadWriteLock类
##### 4.2.1 [类ReentrantReadWriteLock的使用：读读共享](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P236)
##### 4.2.2 [类ReentrantReadWriteLock的使用：写写互斥](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P237)
##### 4.2.3 [类ReentrantReadWriteLock的使用：读写互斥](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter4/P238)

### 第五章 定时器Timer
#### 5.1 定时器Timer的使用
##### 5.1.1 [方法schedule(TimerTask task, Date time)的测试](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter5/P242)
##### 5.1.2 [方法schedule(TimerTask task, Date firstTime, long period)的测试](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter5/P247)
##### 5.1.2 [方法schedule(TimerTask task, long delay)的测试](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter5/P252)

### 第六章 单例模式与多线程
#### 6.1 [立即加载/"饿汉模式"](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter6/P263)
#### 6.2 延迟加载/"懒汉模式"
##### 1. [延迟加载/"懒汉模式"解析](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter6/P264)
##### 2. [延迟加载/"懒汉模式"的缺点](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter6/P265)
##### 3. [延迟加载/"懒汉模式"的解决方案](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter6/P266)
#### 6.3 [使用静态内置类实现单例模式](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter6/P272)
#### 6.4 [序列化与反序列化的单例模式实现](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter6/P273)
#### 6.5 [使用static代码块实现单例模式](https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter6/P275)
#### 6.6 使用enum枚举实现单例模式(https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter6/P276)
#### 6.7 完善使用enum枚举实现
#### 6.8 本章总结

### 第七章 拾遗增补
#### 7.1 线程的状态
##### 7.1.1 验证NEW、RUNNABLE和TERMINATED(https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter7/P281)
##### 7.1.2 验证TIMED_WAITING(https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter7/P282)
##### 7.1.3 验证BLOCKED(https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter7/P283)
##### 7.1.4 验证WAITING(https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter7/P284)
#### 7.2 线程组
##### 7.2.1 线程对象关联线程组：1级关联(https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter7/P286)
##### 7.2.1 线程对象关联线程组：多级关联(https://github.com/wooyeeyii/ThinkInJava/tree/master/MultipleThread/src/com/chapter7/P287)





