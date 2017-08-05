package com.chang.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestClass {  
	public  static void main(String[] args) throws ClassNotFoundException, Exception{  	
		/* һ��������ƻ�ȡ������ַ��� */
		//��1: ����.class  
		Class<TestClassType> testTypeClass = TestClassType.class;  
		System.out.println("testTypeClass---"+testTypeClass);  
		System.out.println(""); 
		
		//��2: Class.forName()  
		Class<?> testTypeForName = Class.forName("com.chang.reflection.TestClassType");   //packageName.ClassName, otherwise class can't be found      
		System.out.println("testForName---"+testTypeForName);  
		System.out.println(""); 
		
		//��3: Object.getClass()  
		TestClassType testGetClass = new TestClassType();  
		Class<? extends TestClassType> testTypeGetClass = testGetClass.getClass();
		System.out.println("testTypeGetClass---"+ testTypeGetClass);  
		System.out.println(""); 
		
		/* �����������󣺻�ȡ����������������Ķ�������newInstance�� */
		Object obj = testTypeClass.newInstance();	//������TestClassType���޲������췽��.
		System.out.println(""); 
		
		//a. ��ȡ������������
		Field[] fs = testTypeClass.getDeclaredFields();  // �����ȡ������.get...
		//����ɱ䳤���ַ����������洢����  
		StringBuffer sb = new StringBuffer();  
		//ͨ��׷�ӵķ�������ÿ������ƴ�ӵ����ַ�����  
		//����ߵ�public����  
		sb.append(Modifier.toString(testTypeClass.getModifiers()) + " class " + testTypeClass.getSimpleName() +"{\n");  
		//��ߵ�ÿһ������  
		for(Field field:fs){  
			sb.append("\t");//�ո�  
			sb.append(Modifier.toString(field.getModifiers())+" ");//������Ե����η�������public��static�ȵ�  
			sb.append(field.getType().getSimpleName() + " ");//���Ե����͵�����  
			sb.append(field.getName()+";\n");//���Ե�����+�س�  
		}
		sb.append("}");  
		System.out.println(sb); 
		System.out.println(""); 
		
		//b. ��ȡָ������
		Field fsSpec = testTypeClass.getDeclaredField("intpri");
		//ʹ�÷�����ƿ��Դ�����ķ�װ�ԣ�����java��������Բ���ȫ
		fsSpec.setAccessible(true);
		//��obj�����intpri���Ը�ֵ"1001"  
		fsSpec.set(obj, 1001);
		System.out.println(fsSpec.get(obj));
		System.out.println(""); 
		
		Method reflectMethodbyName = testTypeGetClass.getMethod("Method", int.class, String.class);
		reflectMethodbyName.invoke(obj, 100, "hundred");
		
	} 
} 
  
 
