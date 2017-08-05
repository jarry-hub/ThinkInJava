package com.chang.aop;

public class ExistClass implements IExistClass {

	@Override
	public void sayHello() {
		// TODO Auto-generated method stub
		System.out.println("------------- hello world.");
	}
	
	@Override
	public void sayHello(String para, int m) {
		// TODO Auto-generated method stub
		System.out.println("------------- " + para + ", " + String.valueOf(m));
	}
	
	public void sayHelloTwice(){
		System.out.println("------------- hello world2.");
		System.out.println("------------- hello world2.");
	}
	
	public String returnHello(String str) {
		return str;
	}

}
