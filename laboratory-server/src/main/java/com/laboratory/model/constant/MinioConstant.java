package com.laboratory.model.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * minio 配置信息
 *
 * @author wb
 */
@Component
public class MinioConstant implements InitializingBean {
    @Value("${minio.url}")
    private String host;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucketName;

    public static String HOST;
    public static String ACCESS_KEY;
    public static String SECRET_KEY;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() {
        HOST = host;
        ACCESS_KEY = accessKey;
        SECRET_KEY = secretKey;
        BUCKET_NAME = bucketName;
    }
}
