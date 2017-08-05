package com.chang.javaSpring;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class ResourceTest {
	
	@Resource
	private ServiceTest serviceTest;
	
	public void resourceMethod() {
		System.out.println("This is class resourceMethod.");
		serviceTest.serviceMethod();
	}

}
