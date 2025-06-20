package com.laboratory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.mapper.ProjectMapper;
import com.laboratory.model.dto.project.ProjectQueryDto;
import com.laboratory.model.entity.Project;
import com.laboratory.model.vo.project.ProjectVO;
import com.laboratory.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
    implements ProjectService{

    @Override
    public List<ProjectVO> getAll(Integer pIndex, Integer pSize, ProjectQueryDto projectQueryDto) {
        PageHelper.startPage(pIndex, pSize);
        return baseMapper.getAll(projectQueryDto);
    }
}




