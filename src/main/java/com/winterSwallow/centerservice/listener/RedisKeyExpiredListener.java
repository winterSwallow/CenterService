package com.winterSwallow.centerservice.listener;

import com.winterSwallow.centerservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.lang.Nullable;

import java.nio.charset.StandardCharsets;

/**
 * Redis过期Key监听回调
 * Name: RedisKeyExpiredListener
 * Author: winterSwallow
 * Date: 2020-05-27 10:23
 */
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {

    @Autowired
    private AuthService authService;

    public RedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        String expiredKey = new String(message.getBody(),StandardCharsets.UTF_8);
        authService.updateAccessToken(expiredKey);
    }
}
