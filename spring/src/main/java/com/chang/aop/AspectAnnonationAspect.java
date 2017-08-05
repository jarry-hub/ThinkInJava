/**
 * 错误经验：
 * 1. 首先不能使用同名的pointcut函数名, 无法识别
 * 2. 
 */


package com.chang.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
//同一个连接点有不同切面，则可通过Order确定执行顺序，越小，优先级越高   (不推荐)
@Order(1)
public class AspectAnnonationAspect {
	
	 @Pointcut(value = "execution(* com.chang.aop.AspectAnnotationClass.*(..))")  
	 public void declareJoinPointExpression() {}
	 
	 @Pointcut(value = "execution(* com.chang.aop.AspectAnnotationClass.sayHello2(..)) && args(para, m)")
	 public void joinPointOfTwoPara(String para, int m) {}
	 
	 //下面这句话会报错，无匹配的切入点
/*	 @Pointcut(value = "execution(* com.chang.aop.AspectAnnotationClass.returnHello(..)) && returning(para)")
	 public void returnFromHello(String para) {}*/
	
	//前置通知  
	@Before(value="declareJoinPointExpression()")
    public void beforeAction() {  
        System.out.println("===========before action."); 
	} 
	
	//法1
	@Before(value="joinPointOfTwoPara(para, m)", argNames="para, m")
    public void beforeAction(String para, int m) {
    	System.out.println("===========before action. para: " + para + ", " + String.valueOf(m)); 
    }
	/********************************************************************************************/
	//法2    通过下面的方法可获得参数，但存在问题，切入点可获得匹配的所有函数，如何确定参数个数以及参数类型，都需慎重匹配.......
	@Before(value="execution(* com.chang.aop.AspectAnnotationClass.sayHello2(..))")
    public void beforeAction2(JoinPoint jp) {
		Object[] obj = jp.getArgs();
		String para = (String) obj[0];
		int m = (int) obj[1];
    	System.out.println("===========before action. para: " + para + ", " + String.valueOf(m)); 
    }
    
    //后置最终通知  
	@After(value="declareJoinPointExpression()")
    public void afterFinallyAction() {  
        System.out.println("===========after finally action.");
    }
	
	//@AfterReturning(value="returnFromHello(para)", returning="para")
	@AfterReturning(returning="para",pointcut="execution(* com.chang.aop.AspectAnnotationClass.returnHello(..))")
    public void afterFinallyAction(String para) {  
        System.out.println("===========after finally action. return value: " + para);
    }

}
