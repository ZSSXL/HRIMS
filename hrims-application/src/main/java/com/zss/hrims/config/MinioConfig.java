package com.zss.hrims.config;

import com.zss.hrims.properties.MinioProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZSS
 * @date 2022/6/29 14:54
 * @desc minio配置
 */
@Configuration
@EnableConfigurationProperties(MinioProperties.class)
public class MinioConfig {

    private final MinioProperties minioProperties;

    @Autowired
    public MinioConfig(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }

}
