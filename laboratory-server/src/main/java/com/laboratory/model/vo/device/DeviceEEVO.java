package com.laboratory.model.vo.device;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;

import java.util.Date;

/**
 * 实验室设备导出视图对象
 *
 * @author lucky
 */

@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@Data
public class DeviceEEVO {

    @ExcelProperty(value = "编号", index = 0)
    private Integer id;

    @ExcelProperty(value = "设备名称", index = 1)
    @ColumnWidth(20)
    private String deviceName;

    @ExcelProperty(value = "购买时间", index = 2)
    @ColumnWidth(20)
    private Date deviceDate;

    @ExcelProperty(value = "设备价格", index = 3)
    @ColumnWidth(20)
    private Integer devicePrice;

    @ExcelProperty(value = "保修时间", index = 4)
    @ColumnWidth(20)
    private String guarantee;

    @ExcelProperty(value = "生产厂商", index = 5)
    @ColumnWidth(30)
    private String deviceFactory;

    @ExcelProperty(value = "实验室名称", index = 6)
    @ColumnWidth(25)
    private String labName;
}
