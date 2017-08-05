package com.chang.inherit;

class Cat implements IAnimal {

	@Override
	public void bark() {
		System.out.println("miao, miao!");
	}

	@Override
	public void walk() {
		System.out.println("With four feet!");	
	}
	
	public void extraMethod() {
		System.out.println("extraMethod!");
	}
	
}

class Duck implements IAnimal {

	@Override
	public void bark() {
		System.out.println("ga, ga!");
	}

	@Override
	public void walk() {
		System.out.println("With two feet!");
	}
	
	public void extraMethod() {
		System.out.println("extraMethod!");
	}
	
}

public class InheritAbout {
	
	public static void main(String[] args) {
		IAnimal animal = new Duck();
		animal.bark();
		
		Duck duck = (Duck)animal;
		duck.extraMethod();
		
		Cat cat = (Cat)animal;
		cat.extraMethod();
		
		
	}
	
}










