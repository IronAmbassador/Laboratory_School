package com.laboratory.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 统一返回结果状态枚举类
 *
 * @author lucky
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    FAIL(201, "操作失败"),
    SERVICE_ERROR(202, "服务异常"),
    DATA_ERROR(204, "数据异常"),
    USERNAME_NOT_FOUND(500, "用户名不存在"),
    USER_DISABLED(500, "账号已被禁用"),
    PASSWORD_ERROR(500, "密码错误"),
    OLD_PASSWORD_ERROR(500, "旧密码错误"),
    PARAM_ERROR(500, "参数错误"),
    TOKEN_ERROR(500, "token错误"),
    FILE_ERROR(500, "文件错误"),
    FILE_NOT_FIND(500, "文件未找到"),
    DATA_EXPORT_ERROR(500, "导出数据失败"),
    DATA_IMPORT_ERROR(500, "导入数据失败"),
    ADMIN_NOT_DELETED(500, "管理员不能删除"),
    CURRENT_USER_NOT_DELETED(500, "当前用户不能删除"),
    ;


    private final Integer code;       //返回码（状态码）

    private final String message;     //返回消息
}
