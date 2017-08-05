package com.chang.wrapper;

public class Test {
	
	
	public static void main(String[] args) {
		Component component = new ConcreteComponent();
		ConcreteDecoratorB concreteDecoratorB = new ConcreteDecoratorB(new ConcreteDecoratorA(component));
		concreteDecoratorB.sampleOperation();
	}

}
