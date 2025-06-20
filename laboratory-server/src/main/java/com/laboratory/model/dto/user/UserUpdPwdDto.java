package com.laboratory.model.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户密码修改参数
 *
 * @author lucky
 * @since 2024-04-14 14:56
 */

@ApiModel(value = "用户密码修改参数")
@Data
public class UserUpdPwdDto {

    @ApiModelProperty(value = "旧密码")
    private String oldPwd;

    @ApiModelProperty(value = "新密码")
    private String newPwd;
}
