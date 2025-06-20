package com.laboratory.model.vo.labBooking;

import com.laboratory.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 实验室预约视图
 *
 * @author wb
 * @since 2024-07-18
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "实验室预约视图对象")
@Data
public class BookingVO extends BaseVO {

    @ApiModelProperty(value = "实验室")
    private String labName;

    @ApiModelProperty(value = "预约人")
    private String username;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "预约时间")
    private String bookingTime;

    @ApiModelProperty(value = "预约状态")
    private Integer bookingStatus;

    @ApiModelProperty(value = "备注")
    private String remark;
}
