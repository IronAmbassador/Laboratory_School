package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.annotation.SysLog;
import com.laboratory.model.dto.course.CourseQueryDto;
import com.laboratory.model.entity.Course;
import com.laboratory.model.enums.OperationTypeEnum;
import com.laboratory.model.vo.course.CourseVo;
import com.laboratory.result.Result;
import com.laboratory.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 实验室课程控制器
 *
 * @author lucky
 */
@Api(tags = "实验室课程管理")
@RestController
@RequestMapping("/admin/course")
public class CourseController extends BaseController<CourseService, Course> {

    @ApiOperation(value = "分页查询所有课程信息", notes = "分页查询所有课程信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<CourseVo>> getPage(@ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
                                              @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
                                              @RequestBody(required = false) CourseQueryDto courseQueryDto) {
        return Result.success(new PageInfo<>(baseService.getAll(pIndex, pSize, courseQueryDto)));
    }

    @ApiOperation(value = "导出课程信息", notes = "导出课程信息")
    @SysLog(type = OperationTypeEnum.EXPORT)
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        baseService.exportData(response);
    }

    @ApiOperation(value = "导入课程信息", notes = "导入课程信息")
    @SysLog(type = OperationTypeEnum.IMPORT)
    @PostMapping("/importData")
    public Result<Object> importData(@RequestParam("filePath") String filePath) {
        baseService.importData(filePath);
        return Result.success("导入成功");
    }
}
