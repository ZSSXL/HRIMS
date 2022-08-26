package com.zss.hrims.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author zss
 * @date 2020/7/15 13:24
 * @desc dozer - 配置类
 */
@Configuration
public class DozerBeanMapperConfig {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setMappingFiles(Collections.singletonList("config/dozer-config.xml"));
        return mapper;
    }
}
