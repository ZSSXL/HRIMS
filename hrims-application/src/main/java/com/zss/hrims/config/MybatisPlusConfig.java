package com.zss.hrims.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZSS
 * @date 2020/3/22 14:32
 * @desc mb 配置文件
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件 - mybatis-plus 3.5.x
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(100L);
        paginationInterceptor.setDbType(DbType.POSTGRE_SQL);
        return paginationInterceptor;
    }
}
