/*
�ڲ������:
    ���ඨ������������ڲ��������ͱ���Ϊ�ڲ��ࡣ
    ����������A�ж�����һ����B����B�����ڲ��ࡣ
 
�ڲ��ķ����ص㣺
    A:�ڲ������ֱ�ӷ����ⲿ��ĳ�Ա������˽�С�
    B:�ⲿ��Ҫ�����ڲ���ĳ�Ա�����봴������
*/

package com.chang.innerclass;

public class ClassLocationDiff {
	
	private int num = 10;
    
    class Inner {
    	String pri = "TTTTTTeeeeessssstttttt";
    	
        public void show() {
            System.out.println(num);
        }
    }
	
	public static void main(String[] args) {
		
		ClassLocationDiff outer = new ClassLocationDiff();
	    outer.method();
	     
	    Inner inner1 = outer.new Inner();
	    inner1.show();
	    System.out.println( inner1.pri);
	    
	    Inner inner2 = outer.new Inner();
	    System.out.println( inner1 == inner2 );
    }

	public void method() {
        //�Ҳ�������
        //show();
		
        Inner i = new Inner();
        i.show();
    }

}
