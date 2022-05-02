package com.bajie.base.http.request;

import lombok.Data;

/**
 * 请求参数
 *
 * @author bajie
 * @date 2022-4-4 12:16 上午
 */
@Data
public class BaseRequest<T> {
    /**
     * 终端id
     */
    protected String pid;

    /**
     * 时间戳
     */
    protected Long timestamp;

    /**
     * 随机字符串
     */
    protected String nonce;

    /**
     * 参数类
     */
    protected T data;

    /**
     * 签名
     */
    protected String sign;

    /**
     * longitude 经度
     */
    protected String lng;

    /**
     * latitude 纬度
     */
    protected String lat;

    /**
     * 版本
     */
    protected String version;

    /**
     * 来源
     */
    protected String source;
}
