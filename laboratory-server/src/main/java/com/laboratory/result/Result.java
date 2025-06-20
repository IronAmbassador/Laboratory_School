package com.laboratory.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 全局统一返回结果类
 * @author qy
 */

@ApiModel(value = "返回结果")
@Data
public class Result<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "消息")
    private String message;

    @ApiModelProperty(value = "数据")
    private T data;


    // 默认构造函数，创建一个空的结果对象
    public Result() {
    }

    /**
     * 创建一个表示失败的结果对象。
     *
     * @return Result<T> 返回一个包含指定数据的失败结果对象
     */
    public static <T> Result<T> failure() {
        return failure((T) null);
    }

    /**
     * 创建一个表示失败的结果对象，包含指定的数据。
     *
     * @param data 失败结果包含的数据
     * @return Result<T> 返回一个包含指定数据的失败结果对象
     */
    public static <T> Result<T> failure(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    /**
     * 创建一个表示失败的结果对象，包含指定的数据。
     *
     * @param message 失败结果包含的错误信息
     * @return Result<T> 返回一个包含指定数据的失败结果对象
     */
    public static <T> Result<T> failure(String message) {
        return build(message);
    }

    /**
     * 创建一个表示失败的结果对象，使用指定的结果代码。
     *
     * @param resultCodeEnum 指定的结果代码枚举
     * @return Result<T> 返回一个使用指定结果代码的失败结果对象
     */
    public static <T> Result<T> failure(ResultCodeEnum resultCodeEnum) {
        return build(null, resultCodeEnum);
    }


    /**
     * 创建一个表示成功的结果对象，不包含数据。
     *
     * @return Result<T> 返回一个空的成功结果对象
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 创建一个表示成功的结果对象，包含指定的数据。
     *
     * @param data 结果对象包含的数据
     * @return Result<T> 返回一个包含指定数据的成功结果对象
     */
    public static <T> Result<T> success(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * 根据提供的数据创建一个结果对象。如果数据非空，将数据设置到结果对象中。
     *
     * @param data 结果对象可能包含的数据
     * @return Result<T> 返回一个可能包含指定数据的结果对象
     */
    public static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (null != data) {
            result.setData(data);
        }
        return result;
    }

    /**
     * 根据提供的数据和结果代码创建一个结果对象。如果数据非空，将数据设置到结果对象中。
     * 同时设置结果代码和对应的消息。
     *
     * @param data           结果对象可能包含的数据
     * @param resultCodeEnum 结果代码枚举，定义了结果的状态和消息
     * @return Result<T> 返回一个包含指定数据和结果代码的结果对象
     */
    public static <T> Result<T> build(T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * 根据提供的消息创建一个结果对象。
     *
     * @param message 结果对象的消息
     * @return Result<T> 返回一个包含指定消息的结果对象
     */
    public static <T> Result<T> build(String message) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }
}
