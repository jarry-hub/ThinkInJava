package com.chang.resource.base;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

public class ResourceLoaderAwareTest {
	
	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("com/chang/resource/base/resourceLoaderAware.xml");
		ResourceBean resourceBean = ctx.getBean(ResourceBean.class);
		ResourceLoader loader = resourceBean.getResourceLoader();
		if(loader instanceof ApplicationContext) {
			System.out.println("loader is instanceof ApplicationContext.");
		}
	}

}
