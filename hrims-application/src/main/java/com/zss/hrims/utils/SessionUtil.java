package com.zss.hrims.utils;

import javax.servlet.http.HttpSession;

/**
 * @author ZSS
 * @date 2020/3/19 21:15
 * @desc httpSession 工具
 */
public class SessionUtil {

    /**
     * 检查session是否为空
     *
     * @param session 管理员session
     * @return Boolean
     */
    public static Boolean checkSession(HttpSession session) {
        Object admin = (Object) session.getAttribute("admin");
        return admin == null;
    }
}
