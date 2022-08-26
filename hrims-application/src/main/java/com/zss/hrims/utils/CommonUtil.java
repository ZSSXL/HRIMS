package com.zss.hrims.utils;

/**
 * @author ZSS
 * @date 2022/8/1 9:13
 * @desc 通用工具
 */
public class CommonUtil {

    /**
     * 面试轮次，数字转中文
     *
     * @param round 轮次
     * @return 中文
     */
    public static String roundParse(int round) {
        String result;
        switch (round) {
            case 1:
                result = "初";
                break;
            case 2:
                result = "二";
                break;
            case 3:
                result = "三";
                break;
            case 4:
                result = "四";
                break;
            case 5:
                result = "五";
                break;
            case 6:
                result = "六";
                break;
            default:
                result = "七";
        }
        return result + "面";
    }
}
