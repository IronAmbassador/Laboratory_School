package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 实验室信息实体类
 *
 * @author lucky
 */
@ApiModel(value = "实验室信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class Info extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "实验室名称")
    private String name;

}