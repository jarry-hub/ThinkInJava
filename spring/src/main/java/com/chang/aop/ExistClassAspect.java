package com.chang.aop;

public class ExistClassAspect {
	
	   //ǰ��֪ͨ  
    public void beforeAction() {  
        System.out.println("===========before action.");  
	} 
    
    public void beforeAction(String str, int m) {
    	System.out.println("===========before action. para: " + str + ", " + String.valueOf(m)); 
    }
    
    
	//��������֪ͨ  
    public void afterFinallyAction() {  
        System.out.println("===========after finally action.");  
    }
    
  //��������֪ͨ  
    public void afterFinallyAction(String str) {  
        System.out.println("===========after finally action. return value: " + str);
    }

}
