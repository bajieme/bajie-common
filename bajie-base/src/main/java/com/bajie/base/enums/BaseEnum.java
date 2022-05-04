package com.bajie.base.enums;

/**
 * 返回接口
 *
 * @author chenjz
 * @since 2020-07-12 8:24 下午
 */
public interface BaseEnum {

    /**
     * 状态码
     *
     * @return int
     */
    int getCode();

    /**
     * 信息
     *
     * @return String
     */
    String getDesc();
}
