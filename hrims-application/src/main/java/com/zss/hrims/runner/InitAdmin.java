package com.zss.hrims.runner;

import com.zss.hrims.define.HrimsConstants;
import com.zss.hrims.model.entity.SystemUser;
import com.zss.hrims.service.SystemUserService;
import com.zss.hrims.utils.DateUtil;
import com.zss.hrims.utils.EncryptionUtil;
import com.zss.hrims.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author ZSS
 * @date 2020/3/19 19:46
 * @desc 初始化管理员
 */
@Slf4j
@Component
public class InitAdmin implements CommandLineRunner {

    private final SystemUserService userService;

    private final static String ADMIN_NAME = "系统管理员";

    private final static String ADMIN_LOGIN_NAME = "admin";

    private final static String ADMIN_PASSWORD = "hrims_pass";

    @Autowired
    public InitAdmin(SystemUserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        Boolean existInDb = userService.isExistInDb(ADMIN_LOGIN_NAME);
        if (existInDb) {
            log.info("The initial administrator already exists");
        } else {
            try {
                Boolean admin = userService.create(SystemUser.builder()
                        .userId(UUIDUtil.getId())
                        .username(ADMIN_NAME)
                        .role(HrimsConstants.Role.ADMIN)
                        .loginName(ADMIN_LOGIN_NAME)
                        .password(EncryptionUtil.encrypt(ADMIN_PASSWORD))
                        .createTime(DateUtil.currentTimestamp())
                        .build());
                if (admin) {
                    log.info("Initial administrator success ...");
                }
            } catch (Exception e) {
                log.error("Init admin error {}", e.getMessage());
            }
        }
    }
}
