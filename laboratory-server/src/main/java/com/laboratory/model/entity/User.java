package com.laboratory.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author lucky
 */
@ApiModel(value = "用户信息")
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "性别")
    private String userSex;

    @ApiModelProperty(value = "头像")
    private String userImg;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

}