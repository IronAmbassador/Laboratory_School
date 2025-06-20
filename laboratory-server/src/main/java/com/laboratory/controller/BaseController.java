package com.laboratory.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.annotation.SysLog;
import com.laboratory.model.enums.OperationTypeEnum;
import com.laboratory.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础控制器
 * - 抽取各个控制器公共方法
 *
 * @author wb
 * @since 2024-08-25
 */
public abstract class BaseController<M extends IService<T>, T> {

    @Autowired
    protected M baseService;


    @ApiOperation(value = "保存", notes = "保存")
    @SysLog(type = OperationTypeEnum.ADD)
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody T entity) {
        return baseService.save(entity) ? Result.success() : Result.failure();
    }


    @ApiOperation(value = "根据主键删除", notes = "根据主键删除")
    @SysLog(type = OperationTypeEnum.DELETE)
    @DeleteMapping("/remove/{id}")
    public Result<Boolean> remove(@ApiParam(value = "主键", required = true, example = "1") @PathVariable Integer id) {
        return baseService.removeById(id) ? Result.success() : Result.failure();
    }


    @ApiOperation(value = "批量删除", notes = "批量删除")
    @SysLog(type = OperationTypeEnum.DELETE_BATCH)
    @DeleteMapping("/batchRemove")
    public Result<Boolean> batchRemove(@ApiParam(value = "主键集合", required = true) @RequestBody List<Integer> idList) {
        return baseService.removeBatchByIds(idList) ? Result.success() : Result.failure();
    }


    @ApiOperation(value = "修改", notes = "修改")
    @SysLog(type = OperationTypeEnum.UPDATE)
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody T entity) {
        return baseService.updateById(entity) ? Result.success() : Result.failure();
    }


    @ApiOperation(value = "根据主键查询", notes = "根据主键查询")
    @GetMapping("/get/{id}")
    public Result<T> get(@ApiParam(value = "主键", required = true, example = "1") @PathVariable Integer id) {
        return Result.success(baseService.getById(id));
    }


    @ApiOperation(value = "查询所有", notes = "查询所有")
    @GetMapping("/list")
    public Result<List<T>> getList() {
        return Result.success(baseService.list());
    }

}
