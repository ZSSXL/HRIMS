package com.zss.hrims.utils;

import com.zss.hrims.define.HrimsConstants;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author ZSS
 * @date 2021/9/6 15:30
 * @desc 时间工具类
 */
@SuppressWarnings("unused")
public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_FORMAT_COMPLETE = "yyyy-MM-dd HH:mm:ss";
    private static final Long MONTH_TIMESTAMP = 30L * 24L * 60 * 60L * 1000L;
    private static final Long WEEK_TIMESTAMP = 7L * 24L * 60 * 60L * 1000L;
    private static final Long ONE_DAY = 24L * 60 * 60L * 1000L;

    /**
     * 获取当前时间戳
     *
     * @return 13位时间戳
     */
    public static String currentTimestamp() {
        return String.valueOf(current());
    }

    /**
     * 当前时间戳 -- 毫秒，13位
     *
     * @return 13位时间戳
     */
    private static long current() {
        return System.currentTimeMillis();
    }

    /**
     * 时间戳转String
     *
     * @param timestamp 时间戳
     * @return String
     */
    public static String timestampToDate(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp));
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(DATE_FORMAT);
    }

    /**
     * 获取多少天前的时间错
     *
     * @return 13位时间戳
     */
    public static String ago(HrimsConstants.DateEnum dateEnum) {
        long result;
        switch (dateEnum) {
            case DAY:
                result = current() - ONE_DAY;
                break;
            case WEEK:
                result = current() - WEEK_TIMESTAMP;
                break;
            case MONTH:
                result = current() - MONTH_TIMESTAMP;
                break;
            case SIX_MONTH:
                result = current() - (MONTH_TIMESTAMP * 6L);
                break;
            case YEAR:
                result = current() - (MONTH_TIMESTAMP * 12L);
                break;
            default:
                // 默认 2022-08-01 00:00:00
                result = 1659283200000L;
        }
        return String.valueOf(result);
    }

    /**
     * 与现在相隔的天数
     *
     * @param timestamp 时间戳
     * @return 先查天数
     */
    public static Integer daysFromNow(String timestamp) {
        long days = System.currentTimeMillis() - Long.parseLong(timestamp);
        return (int) (days / ONE_DAY);
    }

    /**
     * 获取当前时间(完整模式)
     *
     * @return String
     */
    public static String getDateComplete() {
        DateTime dateTime = new DateTime((new Date()));
        return dateTime.toString(DATE_FORMAT_COMPLETE);
    }

    /**
     * 时间戳转String
     *
     * @param timestamp 时间戳
     * @return String
     */
    public static String timestampToDateComplete(String timestamp) {
        Date date = new Date(Long.parseLong(timestamp));
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(DATE_FORMAT_COMPLETE);
    }
}
