package com.laboratory.model.vo.labSub;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;

/**
 * 实验室分室导出视图对象
 *
 * @author lucky
 */
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@Data
public class SubEEVO {

    @ExcelProperty(value = "编号", index = 0)
    private Integer id;

    @ExcelProperty(value = "实验室分室名称", index = 1)
    @ColumnWidth(25)
    private String labName;

    @ExcelProperty(value = "实验室类型", index = 2)
    @ColumnWidth(20)
    private String labType;

    @ExcelProperty(value = "实验室创建年份", index = 3)
    @ColumnWidth(20)
    private String labCreateYear;

    @ExcelProperty(value = "实验室面积", index = 4)
    @ColumnWidth(15)
    private Integer labArea;

    @ExcelProperty(value = "实验室地点", index = 5)
    @ColumnWidth(15)
    private String labAddr;

    @ExcelProperty(value = "座位数", index = 6)
    @ColumnWidth(15)
    private Integer labSeat;

    @ExcelProperty(value = "实验室专业", index = 7)
    @ColumnWidth(90)
    private String labMajor;

    @ExcelProperty(value = "实验室名称", index = 8)
    @ColumnWidth(25)
    private String name;
}
