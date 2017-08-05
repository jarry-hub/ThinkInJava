package com.chang.resource.base;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;

import junit.framework.Assert;

public class ResourceLoaderTest {
	
	@Test
	public void testResourceLoad() {
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("classpatch:com/chang/resource/base/test1.txt");
		//验证返回值是否为ClassPathResource
		if(resource instanceof ClassPathResource) {
			System.out.println("resource is class of ClassPathResource.");
		}
		
		Resource resource2 = loader.getResource("file:com/chang/resource/base/test1.txt");
		//验证返回值是否为ClassPathResource
		if(resource2 instanceof UrlResource) {
			System.out.println("resource2 is class of UrlResource.");
		}
		
		Resource resource3 = loader.getResource("com/chang/resource/base/test1.txt");
		if(resource3 instanceof ClassPathResource) {
			System.out.println("resource3 is class of ClassPathResource.");
		}
	}

}
