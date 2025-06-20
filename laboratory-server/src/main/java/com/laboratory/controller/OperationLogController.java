package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.annotation.SysLog;
import com.laboratory.model.dto.operationLog.OperationLogQueryDto;
import com.laboratory.model.entity.OperationLog;
import com.laboratory.model.enums.OperationTypeEnum;
import com.laboratory.result.Result;
import com.laboratory.service.OperationLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 操作日志控制器
 *
 * @author lucky
 */

@Api(tags = "操作日志管理")
@RestController
@RequestMapping("/admin/operationLog")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;


    @ApiOperation(value = "分页查询所有操作日志", notes = "分页查询所有操作日志")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<OperationLog>> getPage(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody(required = false) OperationLogQueryDto operateLog) {
        return Result.success(operationLogService.getAll(pIndex, pSize, operateLog));
    }


    @ApiOperation(value = "批量删除操作日志", notes = "批量删除操作日志")
    @SysLog(type = OperationTypeEnum.DELETE_BATCH)
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@ApiParam(value = "主键集合", required = true) @RequestBody List<Integer> idList) {
        return operationLogService.removeBatchByIds(idList) ? Result.success() : Result.failure();
    }


    @ApiOperation(value = "导出操作日志信息", notes = "导出操作日志信息")
    @SysLog(type = OperationTypeEnum.EXPORT)
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        operationLogService.exportData(response);
    }

}
