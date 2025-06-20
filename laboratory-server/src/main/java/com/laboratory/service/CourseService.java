package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laboratory.model.dto.course.CourseQueryDto;
import com.laboratory.model.entity.Course;
import com.laboratory.model.vo.course.CourseVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
public interface CourseService extends IService<Course> {

    List<CourseVo> getAll(Integer pIndex, Integer pSize, CourseQueryDto courseQueryDto);

    void exportData(HttpServletResponse response);

    void importData(String filePath);
}
