package com.laboratory.model.vo.repair;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;

import java.util.Date;

/**
 * 设备报修导出视图对象
 *
 * @author wb
 * @since 2025-05-24
 */

@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@Data
public class RepairEEVO {
    
    @ExcelProperty(value = "主键", index = 0)
    private Integer id;

    @ExcelProperty(value = "设备名称", index = 1)
    @ColumnWidth(20)
    private String deviceName;

    @ExcelProperty(value = "报修时间", index =2)
    @ColumnWidth(25)
    private String repairDate;

    @ExcelProperty(value = "报修状态", index = 3)
    @ColumnWidth(15)
    private Integer status;

    @ExcelProperty(value = "报修描述", index = 4)
    @ColumnWidth(20)
    private String description;

    @ExcelProperty(value = "维修人员", index = 5)
    @ColumnWidth(25)
    private String technicianName;
    
    @ExcelProperty(value = "创建人", index = 6)
    @ColumnWidth(20)
    private String createAt;

    @ExcelProperty(value = "创建时间", index = 7)
    @ColumnWidth(25)
    private Date createTime;

    @ExcelProperty(value = "最后修改人", index = 8)
    @ColumnWidth(20)
    private String updateAt;

    @ExcelProperty(value = "最后修改时间", index = 9)
    @ColumnWidth(25)
    private Date updateTime;

}
