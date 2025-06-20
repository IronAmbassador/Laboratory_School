package com.laboratory.model.vo.loginLog;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.laboratory.model.converter.StateConverter;
import lombok.Data;

import java.util.Date;

/**
 * 登录日志导出视图对象
 *
 * @author lucky
 */

@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER)
@Data
public class LoginLogEEVO {

    @ExcelProperty(value = "编号", index = 0)
    private Integer id;

    @ExcelProperty(value = "用户名", index = 1)
    @ColumnWidth(20)
    private String username;

    @ExcelProperty(value = "登录时间", index = 2)
    @ColumnWidth(25)
    private Date loginTime;

    @ExcelProperty(value = "登录IP", index = 3)
    @ColumnWidth(20)
    private String loginIp;

    @ExcelProperty(value = "登录地点", index = 4)
    @ColumnWidth(25)
    private String loginAddr;

    @ExcelProperty(value = "浏览器", index = 5)
    @ColumnWidth(20)
    private String driverName;

    @ExcelProperty(value = "操作系统", index = 6)
    @ColumnWidth(25)
    private String osName;

    @ExcelProperty(value = "登录状态", index = 7, converter = StateConverter.class)
    @ColumnWidth(15)
    private Integer loginState;

    @ExcelProperty(value = "登录结果", index = 8)
    @ColumnWidth(20)
    private String loginResult;
}
