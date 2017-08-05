package com.chang.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

public class AspectAnnotationClass implements IAspecAnnotationClass {
	
	@Override
	public void sayHello0() {
		// TODO Auto-generated method stub
		System.out.println("------------- hello world.");
	}
	
	@Override
	public void sayHello2(String para, int m) {
		// TODO Auto-generated method stub
		System.out.println("------------- " + para + ", " + String.valueOf(m));
	}
	
	public void sayHelloTwice(){
		System.out.println("------------- hello world2.");
		System.out.println("------------- hello world2.");
	}
	
	public String returnHello(String para) {
		return para;
	}

}
