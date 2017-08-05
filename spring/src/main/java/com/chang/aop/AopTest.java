package com.chang.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
	
	@Test
	public void testExistClassAspect() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/chang/aop/aop-beans.xml");
		IExistClass exist = ctx.getBean("existClass", IExistClass.class);
		exist.sayHello();
		System.out.println("");
		
		exist.sayHelloTwice();
		System.out.println("");
		
		exist.sayHello("I am set param", 100);
		System.out.println("");
		
		exist.returnHello("return value: 100");
		System.out.println("");
		
		
	}

}
