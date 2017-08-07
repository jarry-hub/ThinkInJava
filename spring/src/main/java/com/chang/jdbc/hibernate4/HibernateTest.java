package com.chang.jdbc.hibernate4;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.chang.jdbc.data.model.UserModel;

public class HibernateTest {

	private static SessionFactory sessionFactory;

	/******************************************************************/
	@BeforeClass
	public static void beforeClass() {
		String configLocations = "classpath:com/chang/jdbc/hibernate4/applicationContext-hibernate.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(configLocations);
		
		sessionFactory  = ctx.getBean("sessionFactory", SessionFactory.class);
	}

	/******************************************************************/
/*	@Before  
	public void setUp() {  
	  //id自增主键从0开始  
		final String createTableSql = "CREATE TABLE test (" + "`id` INT NOT NULL AUTO_INCREMENT, " + "`name` VARCHAR(100)," + "PRIMARY KEY (`id`))type=InnoDB";  
	    sessionFactory.openSession().  
	  createSQLQuery(createTableSql).executeUpdate();  
	}  
	@After  
	public void tearDown() {  
	    final String dropTableSql = "drop table test";  
	    sessionFactory.openSession().  
	    createSQLQuery(dropTableSql).executeUpdate();  
	}*/

	/******************************************************************/
	@Test  
	public void testFirst() {  
	    Session session = sessionFactory.openSession();  
	    Transaction transaction = null;  
	    try {  
	    	transaction = beginTransaction(session);
	        UserModel model = new UserModel();  
	        model.setName("myName");  
	        session.save(model);
	        transaction.commit();	//提交才能在数据库中生效 
	    } catch (RuntimeException e) {  
	        rollbackTransaction(transaction);  
	        throw e;  
	    } finally {  
	        commitTransaction(session);  
	    }
	}

	private Transaction beginTransaction(Session session) {
	    Transaction transaction = session.beginTransaction();  
	    //transaction.begin();  
	    return transaction;  
	}
	private void rollbackTransaction(Transaction transaction) {  
	   if(transaction != null) {  
	        transaction.rollback();  
	    }
	}
	private void commitTransaction(Session session) {  
	    session.close();  
	}

	/******************************* 使用hibernateTemplate ***********************************/
	/////////////// 失败，无报错，但存不入数据库
	@Test  
	public void testHibernateTemplate() {  
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);  
		final UserModel model = new UserModel();
		model.setName("myNameThroughHibernateTemplate");
		hibernateTemplate.save(model);
		//hibernateTemplate.flush();
		//通过回调允许更复杂操作
		hibernateTemplate.execute(new HibernateCallback<Void>() {
			@Override
			public Void doInHibernate(Session session) throws HibernateException{
				session.save(model);
				return null;
			}
		});
	}

}
