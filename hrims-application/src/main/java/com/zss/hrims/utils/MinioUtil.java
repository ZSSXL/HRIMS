package com.zss.hrims.utils;

import io.minio.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ZSS
 * @date 2022/8/23 13:20
 * @desc minio使用工具
 */
@Slf4j
@Component
public class MinioUtil {

    private final MinioClient minioClient;

    @Value("${minio.bucket:hrims}")
    private String minioBucket;

    @Autowired
    public MinioUtil(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 上传文件
     *
     * @param inputStream 文件输入流
     * @param fullPath    文件全路径
     * @param fileSize    文件大小
     * @param contentType 文件类型
     * @return 是否上传成功
     */
    public Boolean upload(InputStream inputStream, String fullPath, long fileSize, String contentType) {
        try {
            PutObjectArgs putArgs = PutObjectArgs.builder().bucket(minioBucket)
                    .object(fullPath)
                    .stream(inputStream, fileSize, -1)
                    .contentType(contentType)
                    .build();
            minioClient.putObject(putArgs);
            return true;
        } catch (Exception e) {
            log.error("上传文件异常: [{}]", e.getMessage());
            return false;
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param fullPath 文件全路径
     * @return 文件是否存在
     */
    public Boolean doesFileExist(String fullPath) {
        StatObjectArgs statArgs = StatObjectArgs.builder()
                .bucket(minioBucket)
                .object(fullPath)
                .build();
        try {
            // 该方法查不到文件会抛出异常： [Object does not exist]
            minioClient.statObject(statArgs);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 下载
     */

    public void download() {
        try {
            GetObjectArgs getArgs = GetObjectArgs.builder()
                    .bucket("")
                    .object("").build();
            GetObjectResponse object = minioClient.getObject(getArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量获取预览路径
     *
     * @param fullPathList 文件全路径
     * @return 文件地址
     */
    public List<String> previewList(List<String> fullPathList) {
        List<String> result = new ArrayList<>(fullPathList.size());
        fullPathList.forEach(fullPath -> {
            result.add(preview(fullPath));
        });
        return result;
    }

    /**
     * 获取预览地址
     *
     * @param fullPath 全路径
     * @return url
     */
    public String preview(String fullPath) {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .bucket(minioBucket)
                .object(fullPath)
                .method(Method.GET)
                .build();
        try {
            return minioClient.getPresignedObjectUrl(args);
        } catch (Exception e) {
            return "未找到文件";
        }
    }

    /**
     * 删除
     *
     * @param fullPath 文件全路径
     */
    public Boolean remove(String fullPath) {
        try {
            RemoveObjectArgs getArgs = RemoveObjectArgs.builder()
                    .bucket(minioBucket)
                    .object(fullPath).build();
            minioClient.removeObject(getArgs);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
