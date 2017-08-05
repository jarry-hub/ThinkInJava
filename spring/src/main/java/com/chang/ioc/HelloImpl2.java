package com.chang.ioc;

public class HelloImpl2 implements HelloApi {
	private String message = "";
	
	public HelloImpl2(String message) {
		this.message = message;
	}

	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println(message);
	}

}
