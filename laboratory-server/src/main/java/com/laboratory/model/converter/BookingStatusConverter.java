package com.laboratory.model.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

/**
 * 预约状态转换器
 */
public class BookingStatusConverter implements Converter<Integer> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Integer> context) throws Exception {
        Integer value = context.getValue();

        String valueFormat;
        if (value == 0) {
            valueFormat = "审核中";
        } else if (value == 1) {
            valueFormat = "已预约";
        } else if (value == 2) {
            valueFormat = "未通过";
        } else {
            valueFormat = null;
        }

        return new WriteCellData<>(valueFormat);
    }
}
