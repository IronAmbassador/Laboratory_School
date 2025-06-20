package com.laboratory.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作类型枚举
 *
 * @author wb
 * @since 2024-08-25
 */

@Getter
@AllArgsConstructor
public enum OperationTypeEnum {

    /**
     * 添加
     */
    ADD("添加"),

    /**
     * 修改
     */
    UPDATE("修改"),

    /**
     * 删除
     */
    DELETE("删除"),

    /**
     * 批量删除
     */
    DELETE_BATCH("批量删除"),

    /**
     * 查询
     */
    QUERY("查询"),

    /**
     * 导出
     */
    EXPORT("导出"),

    /**
     * 导入
     */
    IMPORT("导入"),

    /**
     * 其他
     */
    OTHER("其他");


    private final String value;
}
