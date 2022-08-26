package com.zss.hrims.anno;

import java.lang.annotation.*;

/**
 * @author ZSS
 * @date 2022/7/28 16:58
 * @desc 校验权限
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface CheckPermission {

    /**
     * 是否校验admin
     */
    boolean admin() default false;
}
