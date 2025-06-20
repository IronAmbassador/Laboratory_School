package com.laboratory.model.dto.project;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课程项目查询参数
 *
 * @author lucky
 */

@ApiModel(value = "课程项目查询参数")
@Data
public class ProjectQueryDto {

    @ApiModelProperty(value = "课程ID")
    private Integer courseId;

    @ApiModelProperty(value = "项目名称")
    private String name;
}