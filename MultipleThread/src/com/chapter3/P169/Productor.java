package com.chapter3.P169;

public class Productor {
	
	private MyStack myStack;
	
	public Productor(MyStack stack) {
		super();
		this.myStack = stack;
	}
	
	public void pushService() {
		myStack.push();
	}

}
