package com.laboratory.config;

import com.laboratory.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目全局配置
 *
 * @author lucky
 * @since 2024-04-01 19:13
 */
@Configuration
public class GlobalConfig implements WebMvcConfigurer {

    /**
     * JWt 配置
     *
     * @param registry 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将需要放行的路径添加到集合中
        List<String> list = new ArrayList<>();
        list.add("/login");             // 登录
        list.add("/admin/user/save");   // 注册
        list.add("/logout");            // 退出登录
        list.add("/file/*");            // 文件上传

        // 接口文档地址
        list.add("/doc.html");
        list.add("/v2/api-docs");
        list.add("/swagger-resources");
        list.add("/webjars/**");


        // 注册拦截器
        registry.addInterceptor(new JwtInterceptor())
                // 拦截路径
                .addPathPatterns("/**")
                // 放行路径
                .excludePathPatterns(list);
    }


    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS") // 支持方法
                .allowedHeaders("*")// 允许请求头
                .exposedHeaders("*");// 暴露出去的响应头
    }

}
