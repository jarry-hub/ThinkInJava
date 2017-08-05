package com.chang.once;

import java.text.SimpleDateFormat;

public class SingleObject {
	
	   private static SimpleDateFormat dateFormat = null;
	   public static void main(String[] args) {
	    // ָ�����ڸ�ʽΪ��λ��/��λ�·�/��λ���ڣ�ע��yyyy/MM/dd���ִ�Сд��
	        dateFormat = new SimpleDateFormat("hh:mm:ss");
	        // ����lenientΪfalse. ����SimpleDateFormat��ȽϿ��ɵ���֤���ڣ�����2007/02/29�ᱻ���ܣ���ת����2007/03/01
	        dateFormat.setLenient(true);
	        
	        String str = "01:12:90";
	        System.out.println(isValidDate(str));
	    }
	    
	   
	   public static boolean isValidDate(String s) {
	        try {
	             System.out.println(dateFormat.parse(s));
	             return true;
	         }
	        catch (Exception e)
	        {
	            // ���throw java.text.ParseException����NullPointerException����˵����ʽ����
	            return false;
	        }
	    }
}
