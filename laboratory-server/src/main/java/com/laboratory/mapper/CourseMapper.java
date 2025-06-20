package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.dto.course.CourseQueryDto;
import com.laboratory.model.entity.Course;
import com.laboratory.model.vo.course.CourseEEVO;
import com.laboratory.model.vo.course.CourseVo;

import java.util.List;

/**
 * @Entity com.springboot.model.pojo.Course
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> getAll(CourseQueryDto courseQueryDto);


    List<CourseEEVO> getExportData();
}




