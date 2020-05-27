package com.winterSwallow.centerservice.service;

import com.alibaba.fastjson.JSON;
import com.winterSwallow.centerservice.base.AccessToken;
import com.winterSwallow.centerservice.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 获取凭证服务
 * Name: AuthService
 * Author: winterSwallow
 * Date: 2020-05-27 10:23
 */
@Service
public class AuthService {

    /**
     * 获取凭证信息的实例
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 缓存凭证信息的实例
     */
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 平台应用配置常量
     */
    @Autowired
    private Constant constant;

    /**
     * 获取凭证信息
     *
     * @return 返回accessToken对象
     */
    public AccessToken getAccessToken() {
        // 获取Redis缓存中Key对应的凭证信息
        AccessToken accessToken = getFromRedis();
        if (isNotNull(accessToken)) {
            return accessToken;
        }

        // 请求服务器获取AccessToken
        synchronized (this) {
            accessToken = getFromRedis();
            if (isNotNull(accessToken)) {
                return accessToken;
            }
            accessToken = updateAccessToken(constant.appId + "-" + constant.appSecret);
        }
        return accessToken;
    }

    /**
     * 获取Redis缓存中的AccessToken对象
     *
     * @return 返回accessToken对象
     */
    private AccessToken getFromRedis() {
        String accessTokenJson = redisTemplate.opsForValue().get(constant.appId + "-" + constant.appSecret);
        return JSON.parseObject(accessTokenJson, AccessToken.class);
    }

    /**
     * 判断Redis缓存中AccessToken对象是否有值
     *
     * @param accessToken
     * @return true为存在，否则为false
     */
    private Boolean isNotNull(AccessToken accessToken) {
        if (accessToken != null && !StringUtils.isEmpty(accessToken.getAccessToken())) {
            return true;
        }
        return false;
    }

    /**
     * 获取并更新凭证信息
     *
     * @param accessTokenKey
     * @return 返回accessToken对象
     */
    public AccessToken updateAccessToken(String accessTokenKey) {
        String requestUrl = constant.accessTokenUrl.replace("APPID", constant.appId).replace("APPSECRET", constant.appSecret);
        ResponseEntity<AccessToken> responseEntity = restTemplate.getForEntity(requestUrl, AccessToken.class);
        AccessToken accessToken = responseEntity.getBody();
        if (accessToken != null && !StringUtils.isEmpty(accessToken.getAccessToken()) && accessToken.getExpiresIn() != null && accessToken.getExpiresIn() > constant.updateTimeInterval) {
            String accessTokenJson = JSON.toJSONString(accessToken);
            redisTemplate.opsForValue().set(accessTokenKey, accessTokenJson, constant.updateTimeInterval, TimeUnit.SECONDS);
        }
        return accessToken;
    }
}
