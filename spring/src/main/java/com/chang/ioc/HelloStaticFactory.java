package com.chang.ioc;

public class HelloStaticFactory {

	//±ØÐëÎª¾²Ì¬º¯Êý
	public static HelloApi newInstance(String message) {  
        return new HelloImpl2(message);  
	} 
}
