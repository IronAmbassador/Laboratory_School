package com.laboratory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.mapper.CourseMapper;
import com.laboratory.mapper.OpenMapper;
import com.laboratory.model.dto.open.OpenQueryDto;
import com.laboratory.model.entity.Open;
import com.laboratory.model.vo.open.OpenVo;
import com.laboratory.service.OpenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class OpenServiceImpl extends ServiceImpl<OpenMapper, Open>
        implements OpenService{

    @Autowired
    private OpenMapper mapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<OpenVo> getAll(Integer pIndex, Integer pSize, OpenQueryDto openQueryDto) {
        PageHelper.startPage(pIndex, pSize);
        return mapper.getAll(openQueryDto);
    }

    /**
     * 保存开放时间（新增或修改）
     * 添加课程冲突检查
     */
    @Override
    public boolean save(Open entity) {
        // 检查该时间段是否有课程安排
        int conflictCount = courseMapper.countConflictCourse(
                entity.getLabId(),
                entity.getStartTime(),
                entity.getStopTime()
        );

        if (conflictCount > 0) {
            throw new RuntimeException("该时间段有课程安排，不能设置为开放时间");
        }

        return super.save(entity);
    }

    /**
     * 修改开放时间
     */
    @Override
    public boolean updateById(Open entity) {
        // 检查该时间段是否有课程安排（排除自身）
        // 注意：这里需要排除当前记录自身，否则修改时会和自己冲突
        int conflictCount = courseMapper.countConflictCourse(
                entity.getLabId(),
                entity.getStartTime(),
                entity.getStopTime()
        );

        if (conflictCount > 0) {
            throw new RuntimeException("该时间段有课程安排，不能设置为开放时间");
        }

        return super.updateById(entity);
    }
}