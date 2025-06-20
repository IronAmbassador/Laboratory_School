package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验室设备实体类
 *
 * @author lucky
 */

@ApiModel(value = "实验室设备")
@Data
@EqualsAndHashCode(callSuper = true)
public class Device extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "购买时间")
    private Date deviceDate;

    @ApiModelProperty(value = "设备价格")
    private Integer devicePrice;

    @ApiModelProperty(value = "保修时间")
    private String guarantee;

    @ApiModelProperty(value = "生产厂商")
    private String deviceFactory;

    @ApiModelProperty(value = "设备状态")
    private Integer deviceStatus;

    @ApiModelProperty(value = "实验室ID")
    private Integer labId;

}