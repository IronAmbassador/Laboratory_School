package com.laboratory.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratory.utils.JwtUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 *  
 * @since 2024-03-03 13:15
 * <p>
 * Jwt拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // options 请求不拦截
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 封装消息
        Map<String, Object> map = new HashMap<>();

        // 获取请求头中的 token
        String token = request.getHeader("token");

        try {
            // 验证令牌
            JwtUtils.verifyToken(token);

            // 判断是否在黑名单中
            if (JwtUtils.isBlacklist(token)) {
                map.put("message", "token已被拉黑");
            }else{
                // 验证成功不拦截
                return true;
            }
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("message", "无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("message", "token过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("message", "算法不匹配");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "token无效");
        }

        // 设置状态
        map.put("state", false);

        // 将 map 转成 json
        String json = new ObjectMapper().writeValueAsString(map);

        // 设置响应编码格式
        response.setContentType("application/json;charset=utf-8");

        // 打印输出流
        PrintWriter pw = response.getWriter();

        // 输出数据
        pw.println(json);

        // 关闭流
        pw.close();

        // 拦截
        return false;
    }
}
