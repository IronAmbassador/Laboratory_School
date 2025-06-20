package com.laboratory.model.dto.person;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 人员查询参数
 *
 * @author lucky
 */

@ApiModel(value = "人员查询参数")
@Data
public class PersonQueryDto {

    @ApiModelProperty(value = "实验室分室ID")
    private Integer labSubId;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "地址")
    private String address;
}
