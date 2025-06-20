package com.laboratory.model.dto.lab;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 实验室查询参数
 *
 * @author lucky
 */
@ApiModel(value = "实验室查询参数")
@Data
public class LabQueryDto {

    @ApiModelProperty(value = "实验室名称")
    private String name;
}
