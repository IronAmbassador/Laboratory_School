package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.model.dto.lab.LabQueryDto;
import com.laboratory.model.entity.Info;
import com.laboratory.result.Result;
import com.laboratory.service.LabService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 实验室控制器
 *
 * @author lucky
 */
@Api(tags = "实验室管理")
@RestController
@RequestMapping("/admin/lab")
public class LabController extends BaseController<LabService, Info> {

    @ApiOperation(value = "分页查询所有实验室信息", notes = "分页查询所有实验室信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<Info>> getPage(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody(required = false) LabQueryDto labQueryDto) {
        return Result.success(baseService.getAll(pIndex, pSize, labQueryDto));
    }

}
