package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实验室开放实体类
 *
 * @author lucky
 */

@ApiModel(value = "实验室开放信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class Open extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "负责人")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "开放时间")
    private Date startTime;

    @ApiModelProperty(value = "关闭时间")
    private Date stopTime;

    @ApiModelProperty(value = "实验室id")
    private Integer labId;

}