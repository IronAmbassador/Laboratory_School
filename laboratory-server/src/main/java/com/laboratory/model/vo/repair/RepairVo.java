package com.laboratory.model.vo.repair;

import com.laboratory.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备报修视图参数
 *
 * @author lucky
 */


@ApiModel(value = "设备报修视图对象")
@EqualsAndHashCode(callSuper = true)
@Data
public class RepairVo extends BaseVO {


    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "报修时间")
    private String repairDate;

    @ApiModelProperty(value = "报修状态")
    private Integer status;

    @ApiModelProperty(value = "报修描述")
    private String description;

    @ApiModelProperty(value = "维修人员")
    private String technicianName;

}
