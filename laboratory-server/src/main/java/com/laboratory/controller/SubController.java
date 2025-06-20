package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.annotation.SysLog;
import com.laboratory.model.dto.labSub.SubQueryDTO;
import com.laboratory.model.entity.Sub;
import com.laboratory.model.enums.OperationTypeEnum;
import com.laboratory.model.vo.labSub.SubVo;
import com.laboratory.result.Result;
import com.laboratory.service.SubService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 实验室分室控制器
 *
 * @author lucky
 */

@Api(tags = "实验室分室管理")
@RestController
@RequestMapping("/admin/labSub")
public class SubController extends BaseController<SubService, Sub> {

    @ApiOperation(value = "分页查询所有分室信息", notes = "分页查询所有分室信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<SubVo>> getPage(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody(required = false) SubQueryDTO labSubQueryDTO) {

        return Result.success(new PageInfo<>(baseService.getAll(pIndex, pSize, labSubQueryDTO)));
    }


    @ApiOperation(value = "导出实验室分室信息", notes = "导出实验室分室信息")
    @SysLog(type = OperationTypeEnum.EXPORT)
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        baseService.exportData(response);
    }

    @ApiOperation(value = "导入实验室分室信息", notes = "导入实验室分室信息")
    @SysLog(type = OperationTypeEnum.IMPORT)
    @PostMapping("/importData")
    public Result<Object> importData(@RequestParam("filePath") String filePath) {
        baseService.importData(filePath);
        return Result.success("导入成功");
    }


    @ApiOperation(value = "获取实验室分室总数", notes = "获取实验室分室总数")
    @GetMapping("total")
    public Result<?> getTotal(){
        return Result.success(baseService.getLabTotal());
    }

    @ApiOperation(value = "获取空闲实验室分室总数", notes = "获取空闲实验室分室总数")
    @GetMapping("freeTotal")
    public Result<?> getFreeTotal(){
        return Result.success(baseService.getFreeTotal());
    }


    @ApiOperation(value ="获取实验室使用率", notes = "获取实验室使用率")
    @GetMapping("getUseCount")
    public Result<?> getUseCount(){
        return Result.success(baseService.getUseCount());
    }
}
