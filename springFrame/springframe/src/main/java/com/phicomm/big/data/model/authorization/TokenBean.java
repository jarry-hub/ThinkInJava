package com.phicomm.big.data.model.authorization;

import java.util.Date;

/**
 * userId + token
 * Created by yufei.liu
 */
public class TokenBean {

    private String token;

    private Date updateTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
