package com.chang.prototype;

//����ԭ�ͽ�ɫ
public class ConcretePrototype implements Prototype {
	
	public Prototype clone() {
		//
		Prototype prototype = new ConcretePrototype();
		return prototype;
	}

}
