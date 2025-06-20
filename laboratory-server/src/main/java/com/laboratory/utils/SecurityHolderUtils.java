package com.laboratory.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 获取当前登录用户信息
 *
 * @author wb
 * @since 2024-07-18
 */
public class SecurityHolderUtils {

    /**
     * 获取当前登录用户ID
     */
    public static Integer getUserId() {
        return Integer.parseInt(Objects.requireNonNull(getClaims("userId")));
    }

    /**
     * 获取当前登录用户名称
     */
    public static String getUsername() {
        return getClaims("nickname");
    }

    private static String getClaims(String key) {
        HttpServletRequest request = CommonUtils.getRequest();
        String token = request.getHeader("token");
        if (StrUtil.isNotEmpty(token)) {
            DecodedJWT decoded = JwtUtils.verifyToken(token);
            return decoded.getClaims().get(key).asString();
        }
        return null;
    }
}
