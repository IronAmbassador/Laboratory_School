package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.model.dto.project.ProjectQueryDto;
import com.laboratory.model.entity.Project;
import com.laboratory.model.vo.project.ProjectVO;

import java.util.List;

/**
 *
 */
public interface ProjectService extends IService<Project> {

    List<ProjectVO> getAll(Integer pIndex, Integer pSize, ProjectQueryDto projectQueryDto);
}
