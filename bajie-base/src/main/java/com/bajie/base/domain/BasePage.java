package com.bajie.base.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author bajie
 * @date 2022-05-02 8:27 下午
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class BasePage<T> implements Serializable {

    private static final long serialVersionUID = 3525250113659719634L;

    /**
     * 页码
     */
    private Long current;

    /**
     * 页大小
     */
    private Long size;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 所有记录
     */
    private List<T> records;
}
