package com.laboratory.aspect;

import com.laboratory.model.dto.user.UserLoginDto;
import com.laboratory.model.entity.LoginLog;
import com.laboratory.model.enums.StateEnum;
import com.laboratory.service.LoginLogService;
import com.laboratory.utils.HttpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 登录日志切面
 *
 * @author wb
 * @since 2025-03-08
 */

@Aspect
@Component
public class LoginLogAspect {

    @Autowired
    private LoginLogService loginLogService;


    @Pointcut("execution(public * com.laboratory.controller.LoginController.login(..))")
    public void loginLog() {
    }

    @AfterReturning(value = "loginLog()")
    public void beforeLoginLog(JoinPoint joinPoint) {
        UserLoginDto userLoginDto = (UserLoginDto) joinPoint.getArgs()[0];
        saveLoginLog(userLoginDto, StateEnum.SUCCESS.getValue(), "登录成功");
    }

    @AfterThrowing(value = "loginLog()", throwing = "e")
    public void afterLoginLog(JoinPoint joinPoint, Exception e) {
        UserLoginDto userLoginDto = (UserLoginDto) joinPoint.getArgs()[0];
        saveLoginLog(userLoginDto, StateEnum.FAILURE.getValue(), e.getMessage());
    }


    /**
     * 保存登录日志
     *
     * @param userLoginDto 登录对象
     * @param loginState   登录状态
     * @param result         描述
     */
    private void saveLoginLog(UserLoginDto userLoginDto, Integer loginState, String result) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(userLoginDto.getName());
        loginLog.setLoginTime(new Date());
        loginLog.setLoginIp(HttpUtils.getIpByNetwork());
        loginLog.setLoginAddr(HttpUtils.getIpPossessionByFile(loginLog.getLoginIp()));
        loginLog.setDriverName(HttpUtils.getBrowserNameAndVersion());
        loginLog.setOsName(System.getProperty("os.name"));
        loginLog.setLoginState(loginState);
        loginLog.setLoginResult(result);

        loginLogService.save(loginLog);
    }
}
