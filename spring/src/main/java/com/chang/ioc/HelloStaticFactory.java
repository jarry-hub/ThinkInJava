package com.chang.ioc;

public class HelloStaticFactory {

	//����Ϊ��̬����
	public static HelloApi newInstance(String message) {  
        return new HelloImpl2(message);  
	} 
}
