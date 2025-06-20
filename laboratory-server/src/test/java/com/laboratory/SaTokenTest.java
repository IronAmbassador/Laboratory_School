package com.laboratory;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaTokenTest {

    @Test
    public void testSaToken() {
        // 模拟用户登录
        // 使用指定账号类型登录
        StpUtil.login(10001);
        // 设置该token的有效期为3600秒（1小时）
//        StpUtil.setTokenTimeout(3600);
        // 打印登录后生成的token信息
        System.out.println("登录后生成的token信息：" + StpUtil.getTokenValue());

        // 验证是否登录成功
        boolean isLogin = StpUtil.isLogin();
        System.out.println("是否登录成功: " + isLogin);

        // 获取当前登录用户的 token 值
        String tokenValue = StpUtil.getTokenValue();
        System.out.println("当前登录用户的 token 值: " + tokenValue);

        // 注销登录
        StpUtil.logout();

        // 再次验证是否登录
        boolean isLoginAfterLogout = StpUtil.isLogin();
        System.out.println("注销登录后是否登录: " + isLoginAfterLogout);

        // 打印 Sa-Token 框架版本号
        System.out.println("Sa-Token 框架版本号: " + SaManager.getLog());
    }
}