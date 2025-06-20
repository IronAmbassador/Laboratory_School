package com.laboratory.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laboratory.mapper.OperationLogMapper;
import com.laboratory.model.dto.operationLog.OperationLogQueryDto;
import com.laboratory.model.entity.OperationLog;
import com.laboratory.model.vo.operationLog.OperationLogEEVO;
import com.laboratory.service.OperationLogService;
import com.laboratory.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
* @author lucky
* @description 针对表【operation_log(操作日志)】的数据库操作Service实现
* @createDate 2024-04-02 20:10:54
*/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>
    implements OperationLogService{

    @Override
    public PageInfo<OperationLog> getAll(Integer pIndex, Integer pSize, OperationLogQueryDto operateLog) {
        // 开启分页
        PageHelper.startPage(pIndex, pSize);

        // 条件参数
        String userName = operateLog.getUsername();
        String operationModule = operateLog.getOperationModule();
        Integer operationState = operateLog.getOperationState();

        // 构造条件
        LambdaQueryWrapper<OperationLog> lqw = new LambdaQueryWrapper<>();
        lqw.like(StrUtil.isNotBlank(userName), OperationLog::getUsername, operateLog.getUsername())
                .like(StrUtil.isNotBlank(operationModule), OperationLog::getOperationModule, operationModule)
                .eq(ObjectUtil.isNotEmpty(operationState), OperationLog::getOperationState, operationState);

        // 根据操作时间倒序
        lqw.orderByDesc(OperationLog::getOperationTime);

        return new PageInfo<>(baseMapper.selectList((lqw)));
    }

    @Override
    public void exportData(HttpServletResponse response) {
        CommonUtils.exportDataToExcel(response, "操作日志", OperationLogEEVO.class, baseMapper.selectList(null));
    }

}




