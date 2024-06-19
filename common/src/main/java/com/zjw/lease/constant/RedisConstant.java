package com.zjw.lease.constant;

/**
 * Redis常量
 */
public class RedisConstant {

    /**
     * 后台管理登录前缀
     */
    public static final String ADMIN_LOGIN_PREFIX = "admin:login:";

    /**
     * 登录验证码过期时间
     */
    public static final Integer ADMIN_LOGIN_CAPTCHA_TTL_SEC = 60;

    /**
     * APP登录前缀
     */
    public static final String APP_LOGIN_PREFIX = "app:login:";

    /**
     * APP登录验证码发送间隔时间
     */
//    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60;
    public static final Integer APP_LOGIN_CODE_RESEND_TIME_SEC = 60 * 10 * 6 * 23;

    /**
     * APP登录验证码过期时间
     */
//    public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10;
    public static final Integer APP_LOGIN_CODE_TTL_SEC = 60 * 10 * 6 * 24;

    /**
     * APP房间前缀
     */
    public static final String APP_ROOM_PREFIX = "app:room:";

    /**
     * 房间详情缓存过期时间
     */
    public static final Integer ROOM_DETAIL_TTL_HOR = 24;
}