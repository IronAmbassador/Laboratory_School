package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.annotation.SysLog;
import com.laboratory.model.dto.loginLog.LoginLogQueryDto;
import com.laboratory.model.entity.LoginLog;
import com.laboratory.model.enums.OperationTypeEnum;
import com.laboratory.result.Result;
import com.laboratory.service.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录日志控制器
 *
 * @author lucky
 */
@Api(tags = "登录日志管理")
@RestController
@RequestMapping("/admin/loginLog")
public class LoginLogController {


    @Autowired
    private LoginLogService loginLogService;

    @ApiOperation(value = "分页查询登录日志", notes = "分页查询登录日志")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<LoginLog>> getPage(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody(required = false) LoginLogQueryDto loginLog) {
        return Result.success(loginLogService.getAll(pIndex, pSize, loginLog));
    }


    @ApiOperation(value = "批量删除登录日志", notes = "批量删除登录日志")
    @SysLog(type = OperationTypeEnum.DELETE_BATCH)
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@ApiParam(value = "登录日志ID集合", required = true) @RequestBody List<Integer> idList) {
        return loginLogService.removeBatchByIds(idList) ? Result.success() : Result.failure();
    }


    @ApiOperation(value = "导出登录日志", notes = "导出登录日志")
    @SysLog(type = OperationTypeEnum.EXPORT)
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        loginLogService.exportData(response);
    }


}
