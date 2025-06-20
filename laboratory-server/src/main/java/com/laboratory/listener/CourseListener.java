package com.laboratory.listener;


import cn.hutool.core.bean.BeanUtil;
import com.laboratory.mapper.CourseMapper;
import com.laboratory.model.entity.Course;
import com.laboratory.model.vo.course.CourseEEVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 课程导入数据监听器
 *
 * @author lucky
 */
@Slf4j
public class CourseListener extends BaseListener<CourseEEVO, CourseMapper> {

    public CourseListener(CourseMapper mapper) {
        super(mapper);
    }

    @Override
    protected void saveData() {
        log.info("{} >> {}条数据，开始存储数据库！", this.getClass().getSimpleName(), cachedDataList.size());
        cachedDataList.forEach(item -> {
            Course course = BeanUtil.copyProperties(item, Course.class, "id");
            // TODO 根据实验室名称查询实验室ID
            course.setLabId(1);
            mapper.insert(course);
        });
    }
}
