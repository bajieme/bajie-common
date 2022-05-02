package com.bajie.boot.constant;

/**
 * 常量类
 *
 * @author bajie
 * @date 2022-05-02 2:59 下午
 * @since 1.0.0
 */
public interface CommonConstants {
    
    /**
     * token过期时间 2小时(7200秒)
     */
    long TOKEN_EXPIRE_TIME = 7200;

    /**
     * timestamp防止DoS攻击 2分钟请求过期
     */
    long TIMESTAMP_EXPIRE_TIME = 2 * 60 * 1000;

    /**
     * 重复提交时间 5秒
     */
    long REPEAT_TIME = 5 * 1000;
}
