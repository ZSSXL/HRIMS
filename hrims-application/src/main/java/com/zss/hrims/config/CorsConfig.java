package com.zss.hrims.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author zhoushs
 * @date 2020/7/29 9:58
 * @desc 允许跨域请求配置
 */
@Configuration
public class CorsConfig {

    /**
     * 使用Filter设置跨域，优先于权限拦截器
     *
     * @return CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许所有的请求域名访问该跨域服务
        corsConfiguration.addAllowedOrigin("*");
        // 允许所有的请求Header访问该跨域服务
        corsConfiguration.addAllowedHeader("*");
        // 允许所有的请求方法访问该跨域服务 如 POST, GET, DELETE, PUT等方法
        corsConfiguration.addAllowedMethod("*");
        // 允许所有的请求携带安全证书
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // 对接口配置跨域支持
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
