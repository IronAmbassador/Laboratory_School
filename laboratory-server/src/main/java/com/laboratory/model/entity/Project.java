package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验室课程项目表
 *
 * @author lucky
 */

@ApiModel(value = "实验室课程项目信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class Project extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "课程ID")
    private Integer courseId;

    @ApiModelProperty(value = "项目名称")
    private String name;

    @ApiModelProperty(value = "项目启动时间")
    private Date startTime;

    @ApiModelProperty(value = "项目结束时间")
    private Date stopTime;

    @ApiModelProperty(value = "项目介绍")
    private String description;

    @ApiModelProperty(value = "项目成员")
    private String personIds;

}