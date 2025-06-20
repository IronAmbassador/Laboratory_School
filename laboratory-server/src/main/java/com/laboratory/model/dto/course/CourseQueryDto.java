package com.laboratory.model.dto.course;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 课程查询参数
 *
 * @author lucky
 */
@ApiModel(value = "课程查询参数")
@Data
public class CourseQueryDto {

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "任课教师")
    private String teacher;

    @ApiModelProperty(value = "实验室ID")
    private String labId;
}
