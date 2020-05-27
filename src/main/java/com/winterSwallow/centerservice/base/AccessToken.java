package com.winterSwallow.centerservice.base;

import java.io.Serializable;

/**
 * 凭证对象
 * Name: AccessToken
 * Author: winterSwallow
 * Date: 2020-05-27 10:23
 */
public class AccessToken implements Serializable {

    private static final long serialVersionUID = -672922170875030109L;

    /**
     * 获取到的凭证
     */
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒
     */
    private Long expiresIn;

    /**
     * 返回错误码
     */
    private Long errcode;

    /**
     * 返回错误信息
     */
    private String errmsg;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getErrcode() {
        return errcode;
    }

    public void setErrcode(Long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
