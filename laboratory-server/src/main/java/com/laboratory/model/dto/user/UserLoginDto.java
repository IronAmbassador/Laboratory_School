package com.laboratory.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录参数
 *
 * @author lucky
 */

@ApiModel(value = "用户登录参数")
@Data
public class UserLoginDto {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String pwd;
}
