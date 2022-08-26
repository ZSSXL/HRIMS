package com.zss.hrims.utils;

/**
 * @author ZSS
 * @date 2022/7/27 13:35
 * @desc 数字类工具
 */
public class NumberUtil {

    /**
     * 阿拉伯数字转中文
     *
     * @param num 阿拉伯数字
     * @return 中文
     */
    public static String numToCh(int num) {
        String str = String.valueOf(num);
        String[] s2 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append(s2[Integer.parseInt(String.valueOf(c))]);
        }
        return sb.toString();
    }
}
