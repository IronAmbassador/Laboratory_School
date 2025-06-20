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
 * 登录日志实体类
 *
 * @author lucky
 */

@ApiModel(value = "登录日志")
@Data
public class LoginLog implements Serializable {

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "登录用户")
    private String username;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @ApiModelProperty(value = "登录地址")
    private String loginAddr;

    @ApiModelProperty(value = "驱动名称")
    private String driverName;

    @ApiModelProperty(value = "操作系统")
    private String osName;

    @ApiModelProperty(value = "登录状态: 0-失败，1-成功")
    private Integer loginState;

    @ApiModelProperty(value = "登录结果")
    private String loginResult;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}