package com.chang.javaSpring;

import org.springframework.stereotype.Service;

@Service("serviceTest")
public class ServiceTest {
	private int count = 0;
	
	public void serviceMethod() {
		System.out.println("This is class ServiceTest, count: " + count);
	}
}
