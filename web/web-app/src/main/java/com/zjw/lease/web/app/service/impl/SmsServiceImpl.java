package com.zjw.lease.web.app.service.impl;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.zjw.lease.web.app.service.ISmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 短信服务接口实现
 * @author 朱俊伟
 * @since 2024/06/16 10:24
 */
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements ISmsService {

    private final Client client;

    /**
     * 发送验证码
     * @param phone 手机号
     * @param verifyCode 验证码
     */
    @Override
    public void sendCode(String phone, String verifyCode) {
        SendSmsRequest smsRequest = new SendSmsRequest();
        smsRequest.setPhoneNumbers(phone);
        smsRequest.setSignName("阿里云短信测试");
        smsRequest.setTemplateCode("SMS_154950909");
        smsRequest.setTemplateParam("{\"code\":\"" + verifyCode + "\"}");
        try {
            client.sendSms(smsRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
