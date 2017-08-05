package com.chang.wrapper;

public class Decorator implements Component {

	private Component component;
	
	public Decorator(Component component) {
		this.component = component;
	}
	
	@Override
	public void sampleOperation() {
		System.out.println("mark. I am from Decorator.");
		
		//ί��Ϊ����
		component.sampleOperation();
	}

}
