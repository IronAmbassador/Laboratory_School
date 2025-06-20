package com.laboratory.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户查询参数
 *
 * @author lucky
 */

@ApiModel(value = "用户查询参数")
@Data
public class UserQueryDto {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String pwd;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
