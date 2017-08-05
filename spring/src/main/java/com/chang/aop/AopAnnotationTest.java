package com.chang.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAnnotationTest {
	
	@Test
	public void testExistClassAspect() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/chang/aop/aop-beans.xml");
		IAspecAnnotationClass exist = ctx.getBean("aspectAnnotationClass", IAspecAnnotationClass.class);
		exist.sayHello0();
		System.out.println("");
		
		exist.sayHelloTwice();
		System.out.println("");
		
		exist.sayHello2("I am set param", 100);
		System.out.println("");
		
		exist.returnHello("return value: 100");
		System.out.println("");
	}

}
