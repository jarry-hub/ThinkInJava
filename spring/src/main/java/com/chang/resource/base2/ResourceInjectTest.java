package com.chang.resource.base2;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class ResourceInjectTest {
	
	@Test  
	public void test() {  
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("com/chang/resource/base2/resourceInject.xml");  
	    ResourceBean3 resourceBean1 = ctx.getBean("resourceBean1", ResourceBean3.class);  
	    ResourceBean3 resourceBean2 = ctx.getBean("resourceBean2", ResourceBean3.class);  
	    Assert.assertTrue((resourceBean1.getResource() instanceof ClassPathResource));  
	    if(resourceBean1.getResource() instanceof ClassPathResource) {
	    	System.out.println("resourceBean1.getResource() instanceof ClassPathResource.");
	    } else {
	    	System.out.println("resourceBean1.getResource() is not instanceof ClassPathResource.");
	    }
	    Assert.assertTrue(resourceBean2.getResource() instanceof ClassPathResource);  
	}  

}
