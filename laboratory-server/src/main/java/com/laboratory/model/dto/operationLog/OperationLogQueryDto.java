package com.laboratory.model.dto.operationLog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 操作日志查询参数
 *
 * @author lucky
 */

@ApiModel(value = "操作日志查询参数")
@Data
public class OperationLogQueryDto {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "操作模块")
    private String operationModule;

    @ApiModelProperty(value = "操作状态")
    private Integer operationState;
}
