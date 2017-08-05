/**
 * �����飺
 * 1. ���Ȳ���ʹ��ͬ����pointcut������, �޷�ʶ��
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
//ͬһ�����ӵ��в�ͬ���棬���ͨ��Orderȷ��ִ��˳��ԽС�����ȼ�Խ��   (���Ƽ�)
@Order(1)
public class AspectAnnonationAspect {
	
	 @Pointcut(value = "execution(* com.chang.aop.AspectAnnotationClass.*(..))")  
	 public void declareJoinPointExpression() {}
	 
	 @Pointcut(value = "execution(* com.chang.aop.AspectAnnotationClass.sayHello2(..)) && args(para, m)")
	 public void joinPointOfTwoPara(String para, int m) {}
	 
	 //������仰�ᱨ����ƥ��������
/*	 @Pointcut(value = "execution(* com.chang.aop.AspectAnnotationClass.returnHello(..)) && returning(para)")
	 public void returnFromHello(String para) {}*/
	
	//ǰ��֪ͨ  
	@Before(value="declareJoinPointExpression()")
    public void beforeAction() {  
        System.out.println("===========before action."); 
	} 
	
	//��1
	@Before(value="joinPointOfTwoPara(para, m)", argNames="para, m")
    public void beforeAction(String para, int m) {
    	System.out.println("===========before action. para: " + para + ", " + String.valueOf(m)); 
    }
	/********************************************************************************************/
	//��2    ͨ������ķ����ɻ�ò��������������⣬�����ɻ��ƥ������к��������ȷ�����������Լ��������ͣ���������ƥ��.......
	@Before(value="execution(* com.chang.aop.AspectAnnotationClass.sayHello2(..))")
    public void beforeAction2(JoinPoint jp) {
		Object[] obj = jp.getArgs();
		String para = (String) obj[0];
		int m = (int) obj[1];
    	System.out.println("===========before action. para: " + para + ", " + String.valueOf(m)); 
    }
    
    //��������֪ͨ  
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
