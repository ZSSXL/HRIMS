package com.zss.hrims;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZSS
 * @date 2022/7/22 14:13
 * @desc 系统启动类
 */
@SpringBootApplication
@MapperScan("com.zss.hrims.mapper")
public class HrimsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrimsApplication.class, args);
    }
}
