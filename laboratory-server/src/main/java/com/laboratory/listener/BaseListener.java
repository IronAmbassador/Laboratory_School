package com.laboratory.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.util.ListUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 基础监听器
 *
 * @author wb
 * @since 2024-08-28
 */
@Slf4j
public abstract class BaseListener<T, E> extends AnalysisEventListener<T> {

    protected final E mapper;

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    /**
     * 缓存的数据
     */
    protected List<T> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public BaseListener(E mapper) {
        this.mapper = mapper;
    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        log.info("{} >> 解析到一条数据:{}", this.getClass().getSimpleName(), t);
        cachedDataList.add(t);

        // 达到BATCH_COUNT了，需要去存储一次数据库
        if (cachedDataList.size() >= BATCH_COUNT) {
            this.saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 确保最后遗留的数据也存储到数据库
        this.saveData();
        log.info("{} >> 所有数据解析完成！", this.getClass().getSimpleName());
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        if (exception instanceof ExcelDataConvertException) {
            // 从0开始计算
            int columnIndex = ((ExcelDataConvertException) exception).getColumnIndex() + 1;
            int rowIndex = ((ExcelDataConvertException) exception).getRowIndex() + 1;
            String message = "第" + rowIndex + "行，第" + columnIndex + "列" + "数据格式有误，请核实";
            throw new RuntimeException(message);
        } else if (exception instanceof RuntimeException) {
            throw exception;
        } else {
            super.onException(exception, context);
        }
    }

    /**
     * 存储数据库
     */
    protected abstract void saveData();
}
