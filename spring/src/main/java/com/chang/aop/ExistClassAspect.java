package com.chang.aop;

public class ExistClassAspect {
	
	   //前置通知  
    public void beforeAction() {  
        System.out.println("===========before action.");  
	} 
    
    public void beforeAction(String str, int m) {
    	System.out.println("===========before action. para: " + str + ", " + String.valueOf(m)); 
    }
    
    
	//后置最终通知  
    public void afterFinallyAction() {  
        System.out.println("===========after finally action.");  
    }
    
  //后置最终通知  
    public void afterFinallyAction(String str) {  
        System.out.println("===========after finally action. return value: " + str);
    }

}
