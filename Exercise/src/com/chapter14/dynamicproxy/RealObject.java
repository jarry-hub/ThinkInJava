package com.chapter14.dynamicproxy;

public class RealObject implements Interface {

	@Override
	public void doSomething() {
		System.out.println("do something.");
		
	}

	@Override
	public void somethingElse(String arg) {
		System.out.println("something else: " + arg);
	}

}
