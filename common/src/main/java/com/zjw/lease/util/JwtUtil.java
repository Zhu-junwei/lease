package com.zjw.lease.util;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTException;
import com.zjw.lease.exception.LeaseException;
import com.zjw.lease.login.LoginUser;
import com.zjw.lease.result.ResultCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Jwt工具类
 */
public class JwtUtil {

    private static final long tokenExpiration = 60 * 60 * 1000L * 24;
    private static final byte[] key = "lease".getBytes();
    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    /**
     * 创建token
     * @param userId 用户id
     * @param username 用户名
     * @return 生成的token
     */
    public static String createToken(Long userId, String username) {
        // HS256(HmacSHA256)算法
        return JWT.create()
                .setExpiresAt(new Date(System.currentTimeMillis() + tokenExpiration))
                .setPayload("userId", userId)
                .setPayload("username", username)
                .setKey(key)
                .sign();
    }

    /**
     * 验证token
     * @param token 访问token
     * @return 是否验证通过
     */
    public static LoginUser verify(String token) {
        if (token == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }
        try {
            JWT jwt = JWT.of(token).setKey(key);
            // 验证签名
            if (!jwt.verify()) {
                throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
            }
            // 验证时间
            if (!jwt.validate(0L)) {
                throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
            }

            JSONObject payloads = jwt.parse(token).getPayloads();
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(payloads.getLong("userId"));
            loginUser.setUsername(payloads.getStr("username"));
            return loginUser;
        } catch (JWTException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}