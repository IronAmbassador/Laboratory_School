package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 实验室分室实体类
 *
 * @author lucky
 */

@ApiModel(value = "实验室分室信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class Sub extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "实验室分室名称")
    private String labName;

    @ApiModelProperty(value = "实验室类型")
    private String labType;

    @ApiModelProperty(value = "实验室创建年份")
    private String labCreateYear;

    @ApiModelProperty(value = "实验室面积")
    private Integer labArea;

    @ApiModelProperty(value = "实验室状态")
    private Integer labStatus;

    @ApiModelProperty(value = "实验室地点")
    private String labAddr;

    @ApiModelProperty(value = "实验室座位数")
    private Integer labSeat;

    @ApiModelProperty(value = "实验室使用专业")
    private String labMajor;

    @ApiModelProperty(value = "实验室id")
    private Integer labId;
}