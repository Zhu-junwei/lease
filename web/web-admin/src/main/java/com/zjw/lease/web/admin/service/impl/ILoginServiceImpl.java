package com.zjw.lease.web.admin.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import com.zjw.lease.constant.RedisConstant;
import com.zjw.lease.exception.LeaseException;
import com.zjw.lease.model.entity.SystemUser;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.result.ResultCodeEnum;
import com.zjw.lease.util.JwtUtil;
import com.zjw.lease.web.admin.mapper.SystemUserMapper;
import com.zjw.lease.web.admin.service.ILoginService;
import com.zjw.lease.web.admin.vo.login.CaptchaVo;
import com.zjw.lease.web.admin.vo.login.LoginVo;
import com.zjw.lease.web.admin.vo.system.user.SystemUserInfoVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 登录服务
 *
 * @author 朱俊伟
 * @since 2024/06/15 08:54
 */
@Service
@RequiredArgsConstructor
public class ILoginServiceImpl implements ILoginService {

    private final StringRedisTemplate redisTemplate;
    private final SystemUserMapper systemUserMapper;

    /**
     * 获取图形验证码
     * @return 图形验证码base64
     */
    @Override
    public CaptchaVo getCaptcha() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 60, 4, 200);
        String code = captcha.getCode();
        String key = IdUtil.fastSimpleUUID();
        String image = captcha.getImageBase64Data();
        redisTemplate.opsForValue().set(RedisConstant.ADMIN_LOGIN_PREFIX + key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);
        return new CaptchaVo(image, key);
    }

    /**
     * 登录
     * @param loginVo 登录信息
     * @return token
     */
    @Override
    public String login(LoginVo loginVo) {
        //1.判断是否输入了验证码
        if (!StringUtils.hasText(loginVo.getCaptchaCode())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }

        //2.校验验证码
        String code = redisTemplate.opsForValue().get(RedisConstant.ADMIN_LOGIN_PREFIX + loginVo.getCaptchaKey());
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }

        if (!code.equalsIgnoreCase(loginVo.getCaptchaCode())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        //3.校验用户是否存在
        SystemUser systemUser = systemUserMapper.selectByUsername(loginVo.getUsername());

        if (systemUser == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }

        //4.校验用户是否被禁
        if (systemUser.getStatus() == BaseStatus.DISABLE) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }

        //5.校验用户密码
        if (!systemUser.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }

        //6.创建并返回TOKEN
        return JwtUtil.createToken(systemUser.getId(), systemUser.getUsername());
    }

    /**
     * 获取登录用户个人信息
     * @param userId 用户id
     * @return 用户信息
     */
    @Override
    public SystemUserInfoVo info(Long userId) {
        SystemUser systemUser = systemUserMapper.selectById(userId);
        SystemUserInfoVo systemUserInfoVo = new SystemUserInfoVo();
        if (systemUser != null) {
            systemUserInfoVo.setName(systemUser.getName());
            systemUserInfoVo.setAvatarUrl(systemUser.getAvatarUrl());
        }
        return systemUserInfoVo;
    }


}
