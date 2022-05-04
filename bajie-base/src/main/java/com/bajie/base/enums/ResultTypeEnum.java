package com.bajie.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 枚举
 *
 * @author bajie
 * @date 2022-05-02 10:38 下午
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum ResultTypeEnum implements BaseEnum {

    /**
     * 请求返回类型
     */
    SUCCESS(0, "success"),
    ERROR(1, "error"),
    WARNING(2, "warning");

    /**
     * code编码
     */
    final int code;
    /**
     * 提示信息
     */
    final String desc;


}
