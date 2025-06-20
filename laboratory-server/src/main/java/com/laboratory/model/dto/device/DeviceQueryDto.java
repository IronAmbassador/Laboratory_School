package com.laboratory.model.dto.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 设备查询参数
 *
 * @author lucky
 */
@ApiModel(value = "设备查询参数")
@Data
public class DeviceQueryDto {

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备最低价格")
    private String minPrice;

    @ApiModelProperty(value = "设备最高价格")
    private String maxPrice;

    @ApiModelProperty(value = "设备状态")
    private Integer deviceStatus;
}
