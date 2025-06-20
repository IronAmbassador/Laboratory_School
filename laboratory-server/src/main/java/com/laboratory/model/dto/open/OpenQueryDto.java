package com.laboratory.model.dto.open;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 实验室开放信息查询参数
 *
 * @author lucky
 */

@ApiModel(value = "实验室开放信息查询参数")
@Data
public class OpenQueryDto {

    @ApiModelProperty(value = "实验室分室ID")
    private Integer labSubId;

    @ApiModelProperty(value = "负责人名称")
    private String name;

    @ApiModelProperty(value = "负责人电话")
    private String phone;
}
