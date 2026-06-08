package com.laboratory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.mapper.CourseMapper;
import com.laboratory.mapper.ProjectMapper;
import com.laboratory.model.dto.project.ProjectQueryDto;
import com.laboratory.model.entity.Course;
import com.laboratory.model.entity.Project;
import com.laboratory.model.vo.project.ProjectVO;
import com.laboratory.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project>
        implements ProjectService{

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<ProjectVO> getAll(Integer pIndex, Integer pSize, ProjectQueryDto projectQueryDto) {
        PageHelper.startPage(pIndex, pSize);
        return baseMapper.getAll(projectQueryDto);
    }

    /**
     * 保存项目（新增）
     * 检查课程对应实验室在项目时间段内是否有课程冲突
     */
    @Override
    public boolean save(Project entity) {
        // 获取课程信息
        Course course = courseMapper.selectById(entity.getCourseId());
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }

        // 检查该课程对应的实验室在项目时间段内是否有其他课程
        // 注意：排除当前课程自身（如果有的话）
        int conflictCount = courseMapper.countConflictCourseExcludeSelf(
                course.getLabId(),
                entity.getStartTime(),
                entity.getStopTime(),
                entity.getCourseId()
        );

        if (conflictCount > 0) {
            throw new RuntimeException("该实验室在项目时间段内有其他课程安排，不能创建项目");
        }

        return super.save(entity);
    }

    /**
     * 修改项目
     */
    @Override
    public boolean updateById(Project entity) {
        // 获取课程信息
        Course course = courseMapper.selectById(entity.getCourseId());
        if (course == null) {
            throw new RuntimeException("课程不存在");
        }

        // 检查该课程对应的实验室在项目时间段内是否有其他课程（排除自身）
        int conflictCount = courseMapper.countConflictCourseExcludeSelf(
                course.getLabId(),
                entity.getStartTime(),
                entity.getStopTime(),
                entity.getCourseId()
        );

        if (conflictCount > 0) {
            throw new RuntimeException("该实验室在项目时间段内有其他课程安排，不能修改项目");
        }

        return super.updateById(entity);
    }
}