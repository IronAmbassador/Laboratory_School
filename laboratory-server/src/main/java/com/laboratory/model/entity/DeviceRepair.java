package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 设备报修实体类
 *
 * @author lucky
 */

@ApiModel(value = "设备报修信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceRepair extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "设备ID")
    private Integer deviceId;

    @ApiModelProperty(value = "故障描述")
    private String description;

    @ApiModelProperty(value = "报修时间")
    private String repairDate;

    @ApiModelProperty(value = "维修技术员")
    private String technicianName;

    @ApiModelProperty(value = "维修状态;1-已维修,2-未维修,3-维修中")
    private Integer status;
}