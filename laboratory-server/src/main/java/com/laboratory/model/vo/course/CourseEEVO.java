package com.laboratory.model.vo.course;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;

import java.util.Date;

/**
 * 课程导出视图对象
 *
 * @author lucky
 */

@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@Data
public class CourseEEVO {

    @ExcelProperty(value = "编号", index = 0)
    private Integer id;

    @ExcelProperty(value = "课程名称", index = 1)
    @ColumnWidth(20)
    private String name;

    @ExcelProperty(value = "教师", index = 2)
    @ColumnWidth(20)
    private String teacher;

    @ExcelProperty(value = "上课时间", index = 3)
    @ColumnWidth(25)
    private Date upTime;

    @ExcelProperty(value = "下课时间", index = 4)
    @ColumnWidth(25)
    private Date downTime;

    @ExcelProperty(value = "实验室", index = 5)
    @ColumnWidth(25)
    private String labName;
}
