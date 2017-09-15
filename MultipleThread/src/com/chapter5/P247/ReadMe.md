#### description
example in this package:
- scheduld(TimerTask task, Date firstTime, long period): 在指定的日期执行任务, 并按指定间隔周期无限制的循环执行。


*** 
```
**说明**
&emsp;&emsp;与上一小节类似：
- 若计划时间早于当前时间，则立即执行，并以当前时间为基准，间隔指定周期后继续执行；
- 若任务的执行时间大于指定周期(例如定时任务睡眠5s, 任务间隔为3s), 则Timer会在上个任务结束时立马开始下个任务；
- TimerTask类中的cancel()方法
    RunTest.java执行结果
    ```
    
    ```






