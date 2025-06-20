package com.laboratory.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.laboratory.model.entity.LoginLog;
import com.laboratory.model.dto.loginLog.LoginLogQueryDto;

import javax.servlet.http.HttpServletResponse;

/**
* @author lucky
* @description 针对表【login_log(登录日志)】的数据库操作Service
* @createDate 2024-04-02 20:10:54
*/
public interface LoginLogService extends IService<LoginLog> {

    PageInfo<LoginLog> getAll(Integer pIndex, Integer pSize, LoginLogQueryDto loginLog);

    void exportData(HttpServletResponse response);
}
