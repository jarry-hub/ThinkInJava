package com.chang.reflection;

public class TestClassType{  
	private int intpri = 0;
	public int intpub = 0;
	private String strpri = "NIL";
	public String strpub = "NIL";
	//���캯��  
	public TestClassType() {  System.out.println("----���캯��---");	}  
	public TestClassType(String str) {	super(); System.out.println(str);	}  
	//��̬�Ĳ�����ʼ��  
	static{  System.out.println("---��̬�Ĳ�����ʼ��---");  }   
	//�Ǿ�̬�Ĳ�����ʼ��   
	{  System.out.println("----�Ǿ�̬�Ĳ�����ʼ��---");  }   
	
	public void printValue() {
		System.out.println("intpri: " + intpri + ", intpub: " + intpub + ", strpri: " + strpri + ", strpub: " + strpub);
	}
	
	public void Method(int a, String b) {
		System.out.println("a: " + a + ", b:" + b);
	}
} 
