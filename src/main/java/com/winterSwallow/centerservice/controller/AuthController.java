package com.winterSwallow.centerservice.controller;

import com.winterSwallow.centerservice.service.AuthService;
import com.winterSwallow.centerservice.base.AccessToken;
import com.winterSwallow.centerservice.base.BaseResponse;
import com.winterSwallow.centerservice.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取凭证 控制器
 * Name: AuthController
 * Author: winterSwallow
 * Date: 2020-05-27 10:23
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * 平台应用配置常量
     */
    @Autowired
    private Constant constant;

    /**
     * 平台应用配置常量
     */
    @Autowired
    private AuthService authService;

    /**
     * 获取凭证信息
     *
     * @return 返回accessToken对象
     */
    @GetMapping("/accessToken")
    public BaseResponse getAccessToken() {
        // 配置文件为空
        if (StringUtils.isEmpty(constant.appId) || StringUtils.isEmpty(constant.appSecret) || StringUtils.isEmpty(constant.accessTokenUrl)) {
            return BaseResponse.getInstance(-1, "App配置信息不完整");
        }
        AccessToken accessToken = authService.getAccessToken();
        return BaseResponse.getInstance(accessToken);
    }
}
