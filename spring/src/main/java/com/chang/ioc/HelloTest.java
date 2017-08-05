package com.chang.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloTest {  

       public static void main(String[] args) {  
             //1、读取配置文件实例化一个IoC容器  
             ApplicationContext context = new ClassPathXmlApplicationContext("spring-servlet.xml");  
             //2、从容器中获取Bean，注意此处完全“面向接口编程，而不是面向实现”  
              HelloApi helloApi = context.getBean("hello", HelloApi.class);  
              //3、执行业务逻辑  
              helloApi.sayHello();  
              
              //使用静态工厂方式实例化Bean
              HelloApi helloApi2 = context.getBean("hello2", HelloApi.class);   
              helloApi2.sayHello();  
              
              //实例工厂方法实例化Bean
              HelloApi helloApi3 = context.getBean("hello3", HelloApi.class);  
              helloApi3.sayHello();  
       }  
}  
