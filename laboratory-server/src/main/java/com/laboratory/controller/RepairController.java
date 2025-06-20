package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.annotation.SysLog;
import com.laboratory.model.dto.repair.RepairQueryDto;
import com.laboratory.model.entity.DeviceRepair;
import com.laboratory.model.enums.OperationTypeEnum;
import com.laboratory.model.vo.repair.RepairVo;
import com.laboratory.result.Result;
import com.laboratory.service.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 设备报修控制器
 *
 * @author lucky
 */

@Api(tags = "设备报修管理")
@RestController
@RequestMapping("/admin/repair")
public class RepairController extends BaseController<RepairService, DeviceRepair> {


    @ApiOperation(value = "分页查询所有设备报修信息", notes = "分页查询所有设备报修信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<RepairVo>> getPage(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody(required = false) RepairQueryDto repairQueryDto) {
        return Result.success(new PageInfo<>(baseService.getAll(pIndex, pSize, repairQueryDto)));
    }


    @ApiOperation(value = "导出设备报修信息", notes = "导出设备报修信息")
    @SysLog(type = OperationTypeEnum.EXPORT)
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        baseService.exportData(response);
    }

    @ApiOperation(value = "导入设备报修信息", notes = "导入设备报修信息")
    @SysLog(type = OperationTypeEnum.IMPORT)
    @PostMapping("/importData")
    public Result<Object> importData(@RequestParam("filePath") String filePath) {
        baseService.importData(filePath);
        return Result.success("导入成功");
    }
}
