package com.laboratory.model.vo.device;

import com.laboratory.model.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 设备视图参数
 *
 * @author lucky
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "设备视图对象")
@Data
public class DeviceVo extends BaseVO {

    @ApiModelProperty(value = "实验室")
    private String labName;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "购买时间")
    private String deviceDate;

    @ApiModelProperty(value = "设备价格")
    private Double devicePrice;

    @ApiModelProperty(value = "保修时间")
    private String guarantee;

    @ApiModelProperty(value = "生产厂商")
    private String deviceFactory;

    @ApiModelProperty(value = "设备状态")
    private Integer deviceStatus;
}
