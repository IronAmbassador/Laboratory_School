package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.model.dto.course.CourseQueryDto;
import com.laboratory.model.entity.Course;
import com.laboratory.model.vo.course.CourseEEVO;
import com.laboratory.model.vo.course.CourseVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @Entity com.springboot.model.pojo.Course
 */
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseVo> getAll(CourseQueryDto courseQueryDto);

    List<CourseEEVO> getExportData();

    // 原有的 countConflictCourse 方法
    @Select("SELECT COUNT(*) FROM lab_course " +
            "WHERE lab_id = #{labId} " +
            "AND is_deleted = 0 " +
            "AND up_time < #{endTime} " +
            "AND down_time > #{startTime}")
    int countConflictCourse(@Param("labId") Integer labId,
                            @Param("startTime") Date startTime,
                            @Param("endTime") Date endTime);

    // 新加的方法
    @Select("SELECT COUNT(*) FROM lab_course " +
            "WHERE lab_id = #{labId} " +
            "AND is_deleted = 0 " +
            "AND id != #{excludeCourseId} " +
            "AND up_time < #{endTime} " +
            "AND down_time > #{startTime}")
    int countConflictCourseExcludeSelf(@Param("labId") Integer labId,
                                       @Param("startTime") Date startTime,
                                       @Param("endTime") Date endTime,
                                       @Param("excludeCourseId") Integer excludeCourseId);
}