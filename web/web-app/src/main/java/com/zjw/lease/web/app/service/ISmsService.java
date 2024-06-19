package com.zjw.lease.web.app.service;

/**
 * 短信服务接口
 * @author 朱俊伟
 * @since 2024/06/16 10:23
 */
public interface ISmsService {

    /**
     * 发送验证码
     * @param phone 手机号
     * @param verifyCode 验证码
     */
    void sendCode(String phone, String verifyCode);
}
