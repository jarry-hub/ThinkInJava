package com.chang.wrapper;

public class ConcreteDecoratorB extends Decorator {

	public ConcreteDecoratorB(Component component) {
		super(component);
	}
	
	@Override
	public void sampleOperation() {
		System.out.println("mark. I am from ConcreteDecoratorB.");
		
		super.sampleOperation();
		
		//д���ҵ��
	}

}
