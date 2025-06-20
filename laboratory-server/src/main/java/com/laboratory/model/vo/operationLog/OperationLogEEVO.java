package com.laboratory.model.vo.operationLog;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.laboratory.model.converter.StateConverter;
import lombok.Data;

import java.util.Date;

/**
 * 操作日志导出视图对象
 *
 * @author lucky
 */

@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@Data
public class OperationLogEEVO {

    @ExcelProperty(value = "编号", index = 0)
    private Integer id;

    @ExcelProperty(value = "用户名", index = 1)
    @ColumnWidth(20)
    private String username;

    @ExcelProperty(value = "操作", index = 2)
    @ColumnWidth(20)
    private String operation;

    @ExcelProperty(value = "操作模块", index = 3)
    @ColumnWidth(20)
    private String operationModule;

    @ExcelProperty(value = "操作时间", index = 4)
    @ColumnWidth(25)
    private Date operationTime;

    @ExcelProperty(value = "操作IP", index = 5)
    @ColumnWidth(20)
    private String operationIp;

    @ExcelProperty(value = "操作地址", index = 6)
    @ColumnWidth(25)
    private String operationAddr;

    @ExcelProperty(value = "操作状态", index = 7, converter = StateConverter.class)
    @ColumnWidth(15)
    private Integer operationState;

    @ExcelProperty(value = "操作结果", index = 8)
    @ColumnWidth(30)
    private String operationResult;
}
