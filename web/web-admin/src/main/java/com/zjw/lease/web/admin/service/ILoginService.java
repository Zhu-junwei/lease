package com.zjw.lease.web.admin.service;

import com.zjw.lease.web.admin.vo.login.CaptchaVo;
import com.zjw.lease.web.admin.vo.login.LoginVo;
import com.zjw.lease.web.admin.vo.system.user.SystemUserInfoVo;

/**
 * <p>
 * 登录服务
 * </p>
 */
public interface ILoginService {

    /**
     * 获取图形验证码
     * @return 图形验证码base64
     */
    CaptchaVo getCaptcha();

    /**
     * 登录
     * @param loginVo 登录信息
     * @return token
     */
    String login(LoginVo loginVo);

    /**
     * 获取登录用户个人信息
     * @param userId 用户id
     * @return 用户信息
     */
    SystemUserInfoVo info(Long userId);
}
