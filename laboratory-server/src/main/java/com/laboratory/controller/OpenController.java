package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.model.dto.open.OpenQueryDto;
import com.laboratory.model.entity.Open;
import com.laboratory.model.vo.open.OpenVo;
import com.laboratory.result.Result;
import com.laboratory.service.OpenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 实验室开放控制器
 *
 * @author lucky
 */
@Api(tags = "实验室开放管理")
@RestController
@RequestMapping("/admin/open")
public class OpenController extends BaseController<OpenService, Open> {

    @ApiOperation(value = "分页查询所有实验室开放信息", notes = "分页查询所有实验室开放信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<OpenVo>> getPage(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody(required = false) OpenQueryDto openQueryDto) {
        return Result.success(new PageInfo<>(baseService.getAll(pIndex, pSize, openQueryDto)));
    }


}
