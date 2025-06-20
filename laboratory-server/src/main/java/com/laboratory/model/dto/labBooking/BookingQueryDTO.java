package com.laboratory.model.dto.labBooking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 实验室预约查询参数
 *
 * @author wb
 * @since 2024-07-18
 */

@ApiModel(value = "实验室预约查询参数")
@Data
public class BookingQueryDTO {

    @ApiModelProperty(value = "用户ID")
    private Integer userId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "实验室ID")
    private Integer labId;

    @ApiModelProperty(value = "预约状态")
    private Integer bookingStatus;

    @ApiModelProperty(value = "预约时间")
    private String[] bookingTimes;

    @ApiModelProperty(value = "预约开始时间")
    private String startTime;

    @ApiModelProperty(value = "预约结束时间")
    private String endTime;

}
