package com.chang.ioc;

public class HelloFactory {
	
	public HelloApi newInstance(String message) {  
        return new HelloImpl2(message);  
	}

}
