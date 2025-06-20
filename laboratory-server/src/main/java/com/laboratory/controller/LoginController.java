package com.laboratory.controller;

import com.laboratory.model.dto.user.UserLoginDto;
import com.laboratory.model.entity.User;
import com.laboratory.result.Result;
import com.laboratory.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 *
 * @author lucky
 * @since 2024-04-14 14:20
 */

@Api(tags = "登录管理")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "登录", notes = "用户登录")
    @PostMapping("/login")
    public Result<Object> login(@RequestBody UserLoginDto userLoginDto) {
        return Result.success(userService.login(userLoginDto));
    }

    @ApiOperation(value = "获取用户信息", notes = "根据 Token 获取用户信息")
    @GetMapping("/info")
    public Result<User> getInfo(String token) {
        return Result.success(userService.getInfo(token));
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @PostMapping("/logout")
    public Result<Object> logout() {
        return Result.success();
    }

}
