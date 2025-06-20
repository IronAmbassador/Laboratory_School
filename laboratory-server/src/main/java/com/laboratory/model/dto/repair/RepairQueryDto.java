package com.laboratory.model.dto.repair;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 设备报修查询参数
 *
 * @author lucky
 */

@ApiModel(value = "设备报修查询参数")
@Data
public class RepairQueryDto {

    @ApiModelProperty(value = "设备ID")
    private Integer deviceId;

    @ApiModelProperty(value = "报修状态")
    private Integer status;

}
