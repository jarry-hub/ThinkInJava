package com.phicomm.big.data.module.luck;

public class TestRequestModel {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TestRequestModel{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
