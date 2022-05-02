package com.bajie.base.exception;

import com.bajie.base.http.response.IResultCode;
import lombok.Getter;

/**
 * 自定义异常
 *
 * @author bajie
 * @date 2022-05-02 3:56 下午
 * @since 1.0.0
 */
@Getter
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 5367915570033962676L;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    public CustomException(String message) {
        this.code = 1;
        this.message = message;
    }

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException(IResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }
}
