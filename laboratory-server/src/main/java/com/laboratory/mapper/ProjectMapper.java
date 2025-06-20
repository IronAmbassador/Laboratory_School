package com.laboratory.mapper;

import com.laboratory.model.dto.project.ProjectQueryDto;
import com.laboratory.model.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.vo.project.ProjectVO;

import java.util.List;

/**
 * @Entity com.springboot.model.pojo.Project
 */
public interface ProjectMapper extends BaseMapper<Project> {

    List<ProjectVO> getAll(ProjectQueryDto projectQueryDto);
}




