package com.bajie.boot.handler;

import com.bajie.base.exception.CustomException;
import com.bajie.base.http.response.R;
import com.bajie.base.http.response.ResultCode;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.Servlet;

/**
 * 全局异常处理
 *
 * @author bajie
 * @date 2022-05-02 3:51 下午
 * @since 1.0.0
 */
@RestControllerAdvice
@ConditionalOnClass({Servlet.class, DispatcherServlet.class})
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<String> handleError(MissingServletRequestParameterException ex) {
        log.debug("缺少请求参数:{}", ex.getMessage());
        String message = String.format("缺少必要的请求参数: %s", ex.getParameterName());
        return R.fail(ResultCode.PARAM_MISS, message, "缺少请求参数");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<String> handleError(MethodArgumentTypeMismatchException ex) {
        log.debug("请求参数格式错误:{}", ex.getMessage());
        String message = String.format("请求参数格式错误: %s", ex.getName());
        return R.fail(ResultCode.PARAM_TYPE_ERROR, message, "请求参数格式错误");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R<String> handleError(NoHandlerFoundException ex) {
        log.debug("404没找到请求:{}", ex.getMessage());
        return R.fail(ResultCode.NOT_FOUND, ex.getMessage(), "404没找到请求");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public R<String> handleError(HttpMediaTypeNotSupportedException ex) {
        log.debug("不支持当前媒体类型:{}", ex.getMessage());
        return R.fail(ResultCode.MEDIA_TYPE_NOT_SUPPORTED, ex.getMessage(), "不支持当前媒体类型");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public R<String> handleError(MethodArgumentNotValidException ex) {
        log.debug("参数验证失败:{}", ex.getMessage());
        return handleError(ex.getBindingResult());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public R<String> handleError(BindException ex) {
        log.debug("参数绑定失败:{}", ex.getMessage());
        return handleError(ex.getBindingResult());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public R<String> handleError(HttpRequestMethodNotSupportedException e) {
        log.debug("不支持当前请求方法:{}", e.getMessage());
        return R.fail(ResultCode.METHOD_NOT_SUPPORTED, e.getMessage(), "不支持当前请求方法");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public R<String> handleError(HttpMessageNotReadableException ex) {
        log.error("消息不能读取:{}", ExceptionUtils.getStackTrace(ex));
        return R.fail(ResultCode.MSG_NOT_READABLE, ex.getMessage(), "消息不能读取");
    }

    @ExceptionHandler(Exception.class)
    public R<String> handleException(Exception ex) {
        log.error("服务器异常:{}", ExceptionUtils.getStackTrace(ex));
        return R.fail(ResultCode.FAILURE, ex.getMessage(), "哎呀出错了，请稍后再试");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> handleCustomException(CustomException ce) {
        log.error("自定义异常:{}", ExceptionUtils.getStackTrace(ce));
        return R.fail(ce.getCode(), ce.getMessage());
    }

    /**
     * 参数校验
     *
     * @param result 、
     * @return 、
     */
    private R<String> handleError(BindingResult result) {
        FieldError error = result.getFieldError();
        assert error != null;
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return R.fail(ResultCode.PARAM_BIND_ERROR, message, "请求参数非法");
    }
}
