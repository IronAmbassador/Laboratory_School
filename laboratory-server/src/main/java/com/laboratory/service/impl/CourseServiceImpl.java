package com.laboratory.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.laboratory.listener.CourseListener;
import com.laboratory.mapper.CourseMapper;
import com.laboratory.model.dto.course.CourseQueryDto;
import com.laboratory.model.entity.Course;
import com.laboratory.model.vo.course.CourseEEVO;
import com.laboratory.model.vo.course.CourseVo;
import com.laboratory.service.CourseService;
import com.laboratory.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course>
        implements CourseService {

    @Override
    public List<CourseVo> getAll(Integer pIndex, Integer pSize, CourseQueryDto courseQueryDto) {
        PageHelper.startPage(pIndex, pSize);
        return baseMapper.getAll(courseQueryDto);
    }


    @Override
    public void exportData(HttpServletResponse response) {
        CommonUtils.exportDataToExcel(response, "课程信息", CourseEEVO.class, baseMapper.getExportData());

    }

    @Override
    public void importData(String filePath) {
        CommonUtils.importDataToExcel(filePath, CourseEEVO.class, new CourseListener(baseMapper));
    }
}




