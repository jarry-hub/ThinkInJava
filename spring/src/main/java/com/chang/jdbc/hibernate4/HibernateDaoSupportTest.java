package com.chang.jdbc.hibernate4;

import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chang.jdbc.dao.UserModelDao;
import com.chang.jdbc.data.model.UserModel;

public class HibernateDaoSupportTest {
	
	private static SessionFactory sessionFactory;
	
	private static UserModelDao userDao;

	/******************************************************************/
	@BeforeClass
	public static void beforeClass() {
		String configLocations = "classpath:com/chang/jdbc/hibernate4/applicationContext-hibernate.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);
		
		sessionFactory  = ctx.getBean("sessionFactory", SessionFactory.class);
		userDao = ctx.getBean(UserModelDao.class);
	}
	
	@Test
	public void testBestPractice() {
		UserModel model = new UserModel();
	    model.setName("test");
	    userDao.save(model);
	    Assert.assertEquals(1, userDao.countAll());
	}

}
