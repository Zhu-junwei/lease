package com.zjw.lease.web.admin.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.zjw.lease.config.MinioProperties;
import com.zjw.lease.web.admin.service.IFileService;
import io.minio.*;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 文件管理实现
 *
 * @author 朱俊伟
 * @since 2024/06/10 00:01
 */
@Service
@AllArgsConstructor
public class FileServiceImpl implements IFileService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        initBucket();
        String fileName = DateUtil.format(new Date(), "yyyMMdd") + "/" + IdUtil.simpleUUID() + "-" + file.getOriginalFilename();
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(fileName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(file.getContentType())
                .build());
        return minioProperties.getEndPoint() + "/" + minioProperties.getBucketName() + "/" + fileName;
    }

    /**
     * 初始化存储桶。
     * 检查存储桶是否存在，如果不存在，则创建存储桶并设置公共读权限。
     * 这个方法确保了存储桶在使用前已经正确配置，适用于需要公共读访问的场景。
     */
    private void initBucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // 检查存储桶是否存在
        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(minioProperties.getBucketName())
                .build());
        if (!bucketExists) {
            // 如果存储桶不存在，则创建存储桶
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            // 为存储桶设置策略，允许公共读取
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .config("""
                            {
                              "Statement" : [ {
                                "Action" : "s3:GetObject",
                                "Effect" : "Allow",
                                "Principal" : "*",
                                "Resource" : "arn:aws:s3:::%s/*"
                              } ],
                              "Version" : "2012-10-17"
                            }
                            """.formatted(minioProperties.getBucketName()))
                    .build());
        }
    }

}
