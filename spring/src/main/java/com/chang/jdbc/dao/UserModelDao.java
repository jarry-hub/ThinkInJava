package com.chang.jdbc.dao;

import com.chang.jdbc.data.model.UserModel;

public interface UserModelDao {
	
    public void save(UserModel model);

    public int countAll();

}
