package com.laboratory.model.dto.loginLog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录日志查询参数
 *
 * @author lucky
 */

@ApiModel(value = "登录日志查询参数")
@Data
public class LoginLogQueryDto {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "登录地址")
    private String loginAddr;

    @ApiModelProperty(value = "登录状态")
    private Integer loginState;
}
