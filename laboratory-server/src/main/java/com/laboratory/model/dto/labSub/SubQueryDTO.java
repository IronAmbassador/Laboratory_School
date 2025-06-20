package com.laboratory.model.dto.labSub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 实验分室查询参数
 *
 * @author lucky
 */

@ApiModel(value = "实验室查询参数")
@Data
public class SubQueryDTO {

    @ApiModelProperty(value = "实验室ID")
    private Integer labId;

    @ApiModelProperty(value = "实验室分室名称")
    private String labName;

    @ApiModelProperty(value = "实验室分室创建年份")
    private String labCreateYear;

    @ApiModelProperty(value = "实验室分室地址")
    private String labAddr;

    @ApiModelProperty(value = "实验室状态")
    private Integer labStatus;
}
