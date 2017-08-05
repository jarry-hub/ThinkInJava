package com.chang.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest {  

       public static void main(String[] args) {  
             //1����ȡ�����ļ�ʵ����һ��IoC����  
             ApplicationContext context = new ClassPathXmlApplicationContext("spring-servlet.xml");  
             //2���������л�ȡBean��ע��˴���ȫ������ӿڱ�̣�����������ʵ�֡�  
              HelloApi helloApi = context.getBean("hello", HelloApi.class);  
              //3��ִ��ҵ���߼�  
              helloApi.sayHello();  
              
              //ʹ�þ�̬������ʽʵ����Bean
              HelloApi helloApi2 = context.getBean("hello2", HelloApi.class);   
              helloApi2.sayHello();  
              
              //ʵ����������ʵ����Bean
              HelloApi helloApi3 = context.getBean("hello3", HelloApi.class);  
              helloApi3.sayHello();  
       }  
}  
