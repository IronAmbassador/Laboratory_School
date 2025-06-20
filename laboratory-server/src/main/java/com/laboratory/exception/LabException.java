package com.laboratory.exception;

import com.laboratory.result.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常处理类
 */

@Data
public class LabException extends RuntimeException {
    // 状态码
    private Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     *
     * @param message 消息
     * @param code    状态码
     */
    public LabException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     *
     * @param resultCodeEnum 枚举类型对象
     */
    public LabException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "LabException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
