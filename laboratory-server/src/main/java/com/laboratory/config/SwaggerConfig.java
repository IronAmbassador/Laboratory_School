package com.laboratory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口文档配置
 *
 * @author wb
 * @since 2024-08-25
 */
@EnableSwagger2 // 开启 swagger
@Configuration
public class SwaggerConfig {

    // swagger 参数
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("DEFAULT")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.laboratory.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    // 文档信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("实验室管理系统接口文档")
                .description("基于 SpringBoot + Vue 开发的实验室管理系统，采用前后端分离模式。")
                .version("v1.0.0")
                .contact(new Contact("wb", "http://localhost:8090/swagger-ui.html", "2332375142@qq.com"))
                .build();
    }
}
