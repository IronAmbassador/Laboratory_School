package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验室课程实体类
 *
 * @author lucky
 */
@ApiModel(value = "实验室课程")
@Data
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "课程名")
    private String name;

    @ApiModelProperty(value = "任课教师")
    private String teacher;

    @ApiModelProperty(value = "上课时间")
    private Date upTime;

    @ApiModelProperty(value = "下课时间")
    private Date downTime;

    @ApiModelProperty(value = "实验室ID")
    private Integer labId;

}