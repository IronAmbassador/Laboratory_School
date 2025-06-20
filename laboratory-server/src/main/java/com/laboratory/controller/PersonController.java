package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.model.dto.person.PersonQueryDto;
import com.laboratory.model.entity.Person;
import com.laboratory.model.vo.person.PersonVo;
import com.laboratory.result.Result;
import com.laboratory.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 实验室人员控制器
 *
 * @author lucky
 */

@Api(tags = "实验室人员管理")
@RestController
@RequestMapping("/admin/person")
public class PersonController extends BaseController<PersonService, Person> {

    @ApiOperation(value = "分页查询所有实验室人员信息", notes = "分页查询所有实验室人员信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<PersonVo>> page(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody(required = false) PersonQueryDto personQueryDto) {
        return Result.success(new PageInfo<>(baseService.getAll(pIndex, pSize, personQueryDto)));
    }


}
