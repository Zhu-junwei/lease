package com.zjw.lease.web.app.service;

import com.zjw.lease.web.app.vo.user.LoginVo;
import com.zjw.lease.web.app.vo.user.UserInfoVo;

/**
 * <p>
 * 登录服务
 * </p>
 */
public interface ILoginService {


    /**
     * 获取短信验证码
     * @param phone 手机号
     */
    void getSMSCode(String phone);

    /**
     * 登录
     * @param loginVo 登录信息
     * @return token
     */
    String login(LoginVo loginVo);

    /**
     * 获取登录用户信息
     * @return 用户信息
     */
    UserInfoVo login();
}
