package com.zss.hrims.utils;

import com.zss.hrims.response.ServerResponse;

/**
 * @author ZSS
 * @date 2022/7/25 9:57
 * @desc 返回信息控制器
 */
public class ReturnUtil {

    /**
     * 布尔值的结果返回
     *
     * @param result     结果
     * @param successMsg 成功消息
     * @param errorMsg   失败消息
     * @return responseMsg
     */
    public static ServerResponse<String> boolReturn(Boolean result, String successMsg, String errorMsg) {
        if (result) {
            return ServerResponse.success(successMsg);
        } else {
            return ServerResponse.error(errorMsg);
        }
    }

    /**
     * 空对象返回
     *
     * @param result   数据
     * @return response
     */
    public static <T> ServerResponse<T> nullReturn(T result, String errorMsg) {
        if (result == null) {
            return ServerResponse.error(errorMsg);
        } else {
            return ServerResponse.success(result);
        }
    }
}
