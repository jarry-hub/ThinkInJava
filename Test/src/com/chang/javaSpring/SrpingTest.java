package com.chang.javaSpring;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SrpingTest {
	
	
	public static void  main(String[] args) {
		
		String locations = "applicationContext.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(locations);
		
		System.out.println(context.containsBean("serviceTest"));
		ServiceTest resource = (ServiceTest) context.getBean("serviceTest");
		System.out.println(resource);
		resource.serviceMethod();
		
		ResourceTest resourceTest = (ResourceTest) context.getBean("resourceTest");
		resourceTest.resourceMethod();
	}
	
	

}
