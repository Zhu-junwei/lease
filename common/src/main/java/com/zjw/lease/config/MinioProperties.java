package com.zjw.lease.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * minio配置信息
 */
@ConfigurationProperties(prefix = "minio")
@Data
public class MinioProperties {

    /**
     * minio地址
     */
    private String endPoint;

    /**
     * 访问密钥(用户名)
     */
    private String accessKey;

    /**
     * 密钥(密码)
     */
    private String secretKey;

    /**
     * 存储桶名称
     */
    private String bucketName;


}
