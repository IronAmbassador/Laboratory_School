package com.laboratory.model.dto.labBooking;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 实验室预约审核参数
 *
 * @author wb
 * @since 2024-07-31
 */

@ApiModel(value = "实验室预约审核参数")
@Data
public class BookingProcessDTO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "状态")
    private Integer bookingStatus;
}
