package com.laboratory.handler;

import com.laboratory.exception.LabException;
import com.laboratory.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author wangbing
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> error(Exception e) {
        e.printStackTrace();
        return Result.failure(e.getMessage());
    }

    @ExceptionHandler(LabException.class)
    @ResponseBody
    public Result<Object> error(LabException e) {
        e.printStackTrace();
        return Result.failure(e.getMessage());
    }
}
