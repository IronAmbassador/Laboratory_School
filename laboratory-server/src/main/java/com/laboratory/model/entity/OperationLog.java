package com.laboratory.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作日志实体类
 *
 * @author lucky
 */

@ApiModel(value = "操作日志")
@Data
public class OperationLog implements Serializable {

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "操作用户")
    private String username;

    @ApiModelProperty(value = "操作类型")
    private String operation;

    @ApiModelProperty(value = "操作模块")
    private String operationModule;

    @ApiModelProperty(value = "操作时间")
    private Date operationTime;

    @ApiModelProperty(value = "操作ip")
    private String operationIp;

    @ApiModelProperty(value = "操作地址")
    private String operationAddr;

    @ApiModelProperty(value = "操作状态: 0-失败，1-成功")
    private Integer operationState;

    @ApiModelProperty(value = "操作结果")
    private String operationResult;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}