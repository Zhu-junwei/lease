package com.zjw.lease.web.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zjw.lease.constant.RedisConstant;
import com.zjw.lease.exception.LeaseException;
import com.zjw.lease.login.LoginUser;
import com.zjw.lease.login.LoginUserHolder;
import com.zjw.lease.model.entity.UserInfo;
import com.zjw.lease.model.enums.BaseStatus;
import com.zjw.lease.result.ResultCodeEnum;
import com.zjw.lease.util.JwtUtil;
import com.zjw.lease.web.app.mapper.UserInfoMapper;
import com.zjw.lease.web.app.service.ILoginService;
import com.zjw.lease.web.app.service.ISmsService;
import com.zjw.lease.web.app.vo.user.LoginVo;
import com.zjw.lease.web.app.vo.user.UserInfoVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
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
    private final ISmsService smsService;
    private final UserInfoMapper userInfoMapper;

    /**
     * 获取短信验证码
     * @param phone 手机号
     */
    @Override
    public void getSMSCode(String phone) {
        //1. 检查手机号码是否为空
        if (StrUtil.isBlank(phone)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }

        //2. 检查Redis中是否已经存在该手机号码的key
        String key = RedisConstant.APP_LOGIN_PREFIX + phone;
        boolean hasKey = Boolean.TRUE.equals(redisTemplate.hasKey(key));
        if (hasKey) {
            //若存在，则检查其存在的时间
            Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            expire = Optional.ofNullable(expire).orElse(0L);
            if (RedisConstant.APP_LOGIN_CODE_TTL_SEC - expire < RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC) {
                //若存在时间不足一分钟，响应发送过于频繁
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }

        //3.发送短信，并将验证码存入Redis
        String verifyCode = RandomUtil.randomNumbers(6);
        smsService.sendCode(phone, verifyCode);
        redisTemplate.opsForValue().set(key, verifyCode, RedisConstant.APP_LOGIN_CODE_TTL_SEC, TimeUnit.SECONDS);
    }

    /**
     * 登录
     * @param loginVo 登录信息
     * @return token
     */
    @Override
    public String login(LoginVo loginVo) {
        // 1.检查手机号码是否为空
        if (StrUtil.isBlank(loginVo.getPhone())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        // 2.检查验证码是否为空
        if (StrUtil.isBlank(loginVo.getCode())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }
        // 3.检查验证码是否正确
        if (!loginVo.getCode().equals(redisTemplate.opsForValue().get(RedisConstant.APP_LOGIN_PREFIX + loginVo.getPhone()))) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }
        // 4.查询用户是否存在，不存在则创建新用户
        UserInfo userInfo = userInfoMapper.selectByPhone(loginVo.getPhone());
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setNickname("用户-"+loginVo.getPhone().substring(6));
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfoMapper.insert(userInfo);
        }
        //4.判断用户是否被禁
        if (userInfo.getStatus().equals(BaseStatus.DISABLE)) {
            throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
        }

        //5.创建并返回TOKEN
        return JwtUtil.createToken(userInfo.getId(), loginVo.getPhone());
    }

    /**
     * 获取登录用户信息
     * @return 用户信息
     */
    @Override
    public UserInfoVo login() {
        LoginUser loginUser = LoginUserHolder.getLoginUser();
        UserInfoVo userInfoVo = new UserInfoVo();
        if (loginUser != null) {
            UserInfo userInfo = userInfoMapper.selectById(loginUser.getUserId());
            BeanUtil.copyProperties(userInfo, userInfoVo);
        }
        return userInfoVo;
    }
}
