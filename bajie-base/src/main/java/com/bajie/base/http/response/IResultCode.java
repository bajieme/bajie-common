package com.bajie.base.http.response;

/**
 * 返回接口
 *
 * @author chenjz
 * @since 2020-07-12 8:24 下午
 */
public interface IResultCode {
    /**
     * 提示信息
     *
     * @return String
     */
    String getMsg();

    /**
     * 状态码
     *
     * @return int
     */
    int getCode();
}
