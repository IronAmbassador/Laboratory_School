package com.laboratory.controller;


import com.github.pagehelper.PageInfo;
import com.laboratory.annotation.SysLog;
import com.laboratory.model.dto.device.DeviceQueryDto;
import com.laboratory.model.entity.Device;
import com.laboratory.model.enums.OperationTypeEnum;
import com.laboratory.model.vo.device.DeviceVo;
import com.laboratory.result.Result;
import com.laboratory.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 实验室设备控制器
 *
 * @author lucky
 */
@Api(tags = "实验室设备管理")
@RestController
@RequestMapping("/admin/device")
public class DeviceController extends BaseController<DeviceService, Device> {

    @ApiOperation(value = "分页查询所有设备信息", notes = "分页查询所有设备信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<DeviceVo>> getPage(@ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
                                              @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
                                              @RequestBody(required = false) DeviceQueryDto deviceQueryDto) {
        return Result.success(new PageInfo<>(baseService.getAll(pIndex, pSize, deviceQueryDto)));
    }


    @ApiOperation(value = "导出设备信息", notes = "导出设备信息")
    @SysLog(type = OperationTypeEnum.EXPORT)
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        baseService.exportData(response);
    }

    @ApiOperation(value = "导入设备信息", notes = "导入设备信息")
    @SysLog(type = OperationTypeEnum.IMPORT)
    @PostMapping("importData")
    public Result<Object> importData(@RequestParam("filePath") String filePath) {
        baseService.importData(filePath);
        return Result.success("导入成功");
    }

    @ApiOperation(value = "获取设备总数", notes = "获取设备总数")
    @GetMapping("total")
    public Result<?> getTotal(){
        return Result.success(baseService.getTotal());
    }

    @ApiOperation(value = "获取设备维修总数", notes = "获取设备维修总数")
    @GetMapping("repairTotal")
    public Result<?> getRepairTotal(){
        return Result.success(baseService.getRepairTotal());
    }


    @ApiOperation(value = "获取设备报修率", notes = "获取设备报修率")
    @GetMapping("getRepairCount")
    public Result<?> getRepairCount(){
        return Result.success(baseService.getRepairCount());
    }
}
