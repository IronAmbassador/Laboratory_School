package com.laboratory.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.laboratory.mapper.LoginLogMapper;
import com.laboratory.model.dto.loginLog.LoginLogQueryDto;
import com.laboratory.model.entity.LoginLog;
import com.laboratory.model.vo.loginLog.LoginLogEEVO;
import com.laboratory.service.LoginLogService;
import com.laboratory.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lucky
 * @description 针对表【login_log(登录日志)】的数据库操作Service实现
 * @createDate 2024-04-02 20:10:54
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog>
        implements LoginLogService {

    @Override
    public PageInfo<LoginLog> getAll(Integer pIndex, Integer pSize, LoginLogQueryDto loginLog) {
        // 开启分页
        PageHelper.startPage(pIndex, pSize);

        // 条件参数
        String username = loginLog.getUsername();
        String loginAddr = loginLog.getLoginAddr();
        Integer loginState = loginLog.getLoginState();

        // 构造条件
        LambdaQueryWrapper<LoginLog> lqw = new LambdaQueryWrapper<>();
        lqw.like(StrUtil.isNotBlank(username), LoginLog::getUsername, username)
                .like(StrUtil.isNotBlank(loginAddr), LoginLog::getLoginAddr, loginAddr)
                .eq(ObjectUtil.isNotEmpty(loginState), LoginLog::getLoginState, loginState);

        // 根据登录时间倒序
        lqw.orderByDesc(LoginLog::getLoginTime);

        return new PageInfo<>(baseMapper.selectList((lqw)));
    }

    @Override
    public void exportData(HttpServletResponse response) {
        CommonUtils.exportDataToExcel(response, "登录日志", LoginLogEEVO.class,  baseMapper.selectList(null));
    }
}




