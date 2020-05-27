package com.winterSwallow.centerservice.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 常量配置
 * Name: Constant
 * Author: winterSwallow
 * Date: 2020-05-27 10:23
 */
@Component
//@PropertySource("file:config/application.properties") // 生产环境
@PropertySource("classpath:application.properties") // 开发环境
public class Constant {

    /**
     * 平台应用Id
     */
    @Value("${appId}")
    public String appId;
    /**
     * 平台应用密钥
     */
    @Value("${appSecret}")
    public String appSecret;
    /**
     * 平台应用授权请求地址
     */
    @Value("${accessTokenUrl}")
    public String accessTokenUrl;
    /**
     * 平台应用授权请求地址
     */
    @Value("${updateTimeInterval}")
    public Long updateTimeInterval;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public Long getUpdateTimeInterval() {
        return updateTimeInterval;
    }

    public void setUpdateTimeInterval(Long updateTimeInterval) {
        this.updateTimeInterval = updateTimeInterval;
    }
}
