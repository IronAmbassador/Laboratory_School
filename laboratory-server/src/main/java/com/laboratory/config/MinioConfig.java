package com.laboratory.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 配置
 *
 * @author wb
 */

@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    private String host;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(host)
                .credentials(accessKey, secretKey)
                .build();
    }
}
