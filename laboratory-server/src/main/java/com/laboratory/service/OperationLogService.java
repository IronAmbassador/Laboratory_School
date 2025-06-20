package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.laboratory.model.entity.OperationLog;
import com.laboratory.model.dto.operationLog.OperationLogQueryDto;

import javax.servlet.http.HttpServletResponse;

/**
* @author lucky
* @description 针对表【operation_log(操作日志)】的数据库操作Service
* @createDate 2024-04-02 20:10:54
*/
public interface OperationLogService extends IService<OperationLog> {

    PageInfo<OperationLog> getAll(Integer pIndex, Integer pSize, OperationLogQueryDto operateLog);

    void exportData(HttpServletResponse response);
}
