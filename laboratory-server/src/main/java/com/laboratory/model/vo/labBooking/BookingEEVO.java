package com.laboratory.model.vo.labBooking;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import com.laboratory.model.converter.BookingStatusConverter;
import lombok.Data;

import java.util.Date;

/**
 * 实验室预约导出视图对象
 *
 * @author wb
 * @since 2024-07-18
 */
@Data
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER, verticalAlignment = VerticalAlignmentEnum.CENTER) // 内容居中
public class BookingEEVO {

    @ExcelProperty(value = "编号", index = 0)
    private Integer id;

    @ExcelProperty(value ="预约用户", index = 1)
    @ColumnWidth(15)
    private String username;

    @ExcelProperty(value = "预约实验室", index = 2)
    @ColumnWidth(30)
    private String labName;

    @ExcelProperty(value = "预约时间", index = 3)
    @ColumnWidth(45)
    private String bookingTime;

    @ExcelProperty(value = "预约状态", index = 4, converter = BookingStatusConverter.class)
    @ColumnWidth(15)
    private Integer bookingStatus;

    @ExcelProperty(value = "备注", index = 5)
    @ColumnWidth(40)
    private String remark;

    @ExcelProperty(value = "创建人", index = 6)
    @ColumnWidth(15)
    private String createAt;

    @ExcelProperty(value = "创建时间", index = 7)
    @ColumnWidth(25)
    private Date createTime;

    @ExcelProperty(value = "最后更新人", index = 8)
    @ColumnWidth(15)
    private String updateAt;

    @ExcelProperty(value = "最后更新时间", index = 9)
    @ColumnWidth(25)
    private Date updateTime;
}
