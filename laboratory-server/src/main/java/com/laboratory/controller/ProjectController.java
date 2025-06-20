package com.laboratory.controller;

import com.github.pagehelper.PageInfo;
import com.laboratory.model.dto.project.ProjectQueryDto;
import com.laboratory.model.entity.Project;
import com.laboratory.model.vo.project.ProjectVO;
import com.laboratory.result.Result;
import com.laboratory.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 课程项目控制器
 *
 * @author lucky
 */

@Api(tags = "课程项目管理")
@RestController
@RequestMapping("/admin/project")
public class ProjectController extends BaseController<ProjectService, Project> {

    @ApiOperation(value = "分页查询所有课程项目信息", notes = "分页查询所有课程项目信息")
    @PostMapping("/page/{pIndex}/{pSize}")
    public Result<PageInfo<ProjectVO>> getPage(
            @ApiParam(value = "当前页码", required = true, example = "1") @PathVariable Integer pIndex,
            @ApiParam(value = "每页显示数量", required = true, example = "10") @PathVariable Integer pSize,
            @RequestBody(required = false) ProjectQueryDto projectQueryDto) {
        return Result.success(new PageInfo<>(baseService.getAll(pIndex, pSize, projectQueryDto)));
    }

}
