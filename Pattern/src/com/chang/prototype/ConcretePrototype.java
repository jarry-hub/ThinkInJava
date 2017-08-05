package com.chang.prototype;

//具体原型角色
public class ConcretePrototype implements Prototype {
	
	public Prototype clone() {
		//
		Prototype prototype = new ConcretePrototype();
		return prototype;
	}

}
