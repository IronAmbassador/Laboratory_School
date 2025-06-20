package com.laboratory.controller;

import cn.hutool.crypto.digest.DigestUtil;
import com.github.pagehelper.PageInfo;
import com.laboratory.annotation.SysLog;
import com.laboratory.model.dto.user.UserQueryDto;
import com.laboratory.model.dto.user.UserUpdPwdDto;
import com.laboratory.model.entity.User;
import com.laboratory.model.enums.OperationTypeEnum;
import com.laboratory.result.Result;
import com.laboratory.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 *
 * @author lucky
 * @version 1.0
 * @since 2023-04-14 18:22
 */

@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/user")
public class UserController extends BaseController<UserService, User> {

    @ApiOperation(value = "分页查询所有用户信息", notes = "分页查询所有用户信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<User>> getAll(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody UserQueryDto userQueryDto) {
        return Result.success(baseService.getAll(pIndex, pSize, userQueryDto));
    }


    @ApiOperation(value = "修改个人信息", notes = "修改个人信息")
    @SysLog(type = OperationTypeEnum.UPDATE)
    @PutMapping("/profile")
    public Result<Boolean> profile(@RequestBody User u) {
        return baseService.updateById(u) ? Result.success() : Result.failure();
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @SysLog(type = OperationTypeEnum.UPDATE)
    @PutMapping("/updPwd")
    public Result<Boolean> updPwd(@RequestBody UserUpdPwdDto userUpdPwdDto) {
        return baseService.updatePwd(userUpdPwdDto) ? Result.success() : Result.failure();
    }


    @ApiOperation(value = "重置密码", notes = "重置密码")
    @SysLog(type = OperationTypeEnum.UPDATE)
    @GetMapping("/resetPwd/{id}")
    public Result<Boolean> restPwd(@ApiParam(value = "主键", required = true, example = "1") @PathVariable Integer id) {
        User u = new User();
        u.setId(id);
        u.setPassword(DigestUtil.md5Hex("123456"));
        return baseService.updateById(u) ? Result.success() : Result.failure();
    }
}
