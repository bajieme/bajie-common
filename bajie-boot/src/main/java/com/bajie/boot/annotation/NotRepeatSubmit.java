package com.bajie.boot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.bajie.boot.constant.CommonConstants.REPEAT_TIME;

/**
 * @author bajie
 * @date 2022-05-02 2:58 下午
 * @since 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotRepeatSubmit {

    /**
     * 过期时间，单位毫秒,默认5秒
     **/
    long value() default REPEAT_TIME;
}
