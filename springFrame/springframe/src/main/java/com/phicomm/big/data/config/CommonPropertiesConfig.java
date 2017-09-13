package com.phicomm.big.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 通用配置
 * <p>
 * Created by yufei.liu
 */
@Component
public class CommonPropertiesConfig {

    @Value("${app.id}")
    private String appId;

    @Value("${environment}")
    private String environment;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}

