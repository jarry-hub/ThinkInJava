package com.chang.jdbc.dao.impl;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.chang.jdbc.dao.UserModelDao;
import com.chang.jdbc.data.model.UserModel;

public class UserModelHibernateDaoImpl extends HibernateDaoSupport implements UserModelDao {

	private static final String COUNT_ALL_HQL = "select count(*) from UserModel"; 
	
    @Override  
    public void save(UserModel model) {  
        getHibernateTemplate().save(model);
    }
    
    @Override  
    public int countAll() {  
        Number count = (Number) getHibernateTemplate().find(COUNT_ALL_HQL).get(0);  
        return count.intValue();  
    } 

}
