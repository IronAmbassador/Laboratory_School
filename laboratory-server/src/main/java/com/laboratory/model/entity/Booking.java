package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验室预约实体类
 *
 * @author lucky
 */
@ApiModel(value = "实验室预约")
@Data
@EqualsAndHashCode(callSuper = true)
public class Booking extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "实验室id")
    private Integer labId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "预约开始时间")
    private Date startTime;

    @ApiModelProperty(value = "预约结束时间")
    private Date endTime;

    @ApiModelProperty(value = "预约状态: 0-审核中，1-已预约，2-未通过")
    private Integer bookingStatus;

    @ApiModelProperty(value = "备注")
    private String remark;
}