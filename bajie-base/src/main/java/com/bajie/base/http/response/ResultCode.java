package com.bajie.base.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 常用状态码
 *
 * @author bajie
 * @date 2022-4-4 12:07 上午
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {
    /**
     * 操作成功 0
     */
    SUCCESS(0, "操作成功"),

    /**
     * 操作失败
     */
    UNKNOW_ERROR(1, "哎呀出错了，请稍后再试!"),

    /**
     * 业务异常 400
     */
    FAILURE(BAD_REQUEST.value(), "业务异常"),

    /**
     * 请求未授权 401
     */
    UN_AUTHORIZED(UNAUTHORIZED.value(), "请求未授权"),

    /**
     * 请求被拒绝 403
     */
    REQ_REJECT(FORBIDDEN.value(), "请求被拒绝,权限不足"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(SC_NOT_FOUND, "404 没找到请求"),

    /**
     * 消息不能读取 400
     */
    MSG_NOT_READABLE(SC_BAD_REQUEST, "消息不能读取"),

    /**
     * 不支持当前请求方法 405
     */
    METHOD_NOT_SUPPORTED(SC_METHOD_NOT_ALLOWED, "不支持当前请求方法"),

    /**
     * 不支持当前媒体类型 415
     */
    MEDIA_TYPE_NOT_SUPPORTED(SC_UNSUPPORTED_MEDIA_TYPE, "不支持当前媒体类型"),

    /**
     * 服务器异常 500
     */
    INTERNAL_SERVER_ERROR(SC_INTERNAL_SERVER_ERROR, "服务器异常"),

    /**
     * 缺少必要的请求参数 400
     */
    PARAM_MISS(SC_BAD_REQUEST, "缺少必要的请求参数"),

    /**
     * 请求参数类型错误 400
     */
    PARAM_TYPE_ERROR(SC_BAD_REQUEST, "请求参数类型错误"),

    /**
     * 请求参数绑定错误 400
     */
    PARAM_BIND_ERROR(SC_BAD_REQUEST, "请求参数绑定错误"),

    /**
     * 参数校验失败
     */
    PARAM_VALID_ERROR(SC_BAD_REQUEST, "参数校验失败"),

    /**
     * 签名错误
     */
    SIGN_ERROR(600, "签名错误"),

    /**
     * 自定义异常
     */
    CUSTOM_ERROR(10001, "自定义异常"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(10002, "参数错误"),

    /**
     * 认证过期 10003
     */
    TOKEN_EXPIRE(10003, "token认证过期"),

    /**
     * 请求超时 10004
     */
    REQUEST_TIMEOUT(10004, "请求超时"),

    /**
     * 防止重复提交 10006
     */
    REPEAT_SUBMIT(10006, "请不要频繁操作"),

    /**
     * fallback 提示信息
     */
    FALLBACK_MSG(SC_BAD_REQUEST, "服务器繁忙请稍后再试!"),
    ;

    /**
     * code编码
     */
    final int code;
    /**
     * 提示信息
     */
    final String msg;
}
