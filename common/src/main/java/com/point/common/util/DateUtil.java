package com.point.common.util;



import com.point.common.exception.DevelopmentException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期相关工具类
 */
public class DateUtil {

    /**
     * 默认日期格式
     */
    private static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat("yyyyMMdd");

	/**
     * 默认日期格式
     */
    public static final SimpleDateFormat defaultFormat = new SimpleDateFormat("dd");

    /**
     * 默认时间格式
     */
    private static final SimpleDateFormat defaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    /**
     * 默认时间格式
     */
    private static final SimpleDateFormat defaultTimeFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 时间格式
     */
    private static final SimpleDateFormat defaultTimeOnlyFormat = new SimpleDateFormat("HHmmssSSS");

    /**
     * 时间格式
     */
    public static final SimpleDateFormat defaultTimeDateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 默认日期+时间格式
     */
    private static final SimpleDateFormat defaultDateTimeOnlyFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 将日期+时间格式时间转化为日期
     *
     *
     * @param str 日期+时间格式
     * @return 日期
     */
    public static Date getDateFromDateTimeFormat(String str) {
        try {
            return defaultDateTimeOnlyFormat.parse(str);
        } catch (ParseException e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * 获取当前系统时间
     *
     * @return 当前系统时间
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前系统时间
     *
     * @return 当前系统时间
     */
    public static String getCurrentDateDefaultFormat() {
        Date systemDate = getCurrentDate();
        return defaultDateFormat.format(systemDate);
    }

    /**
     * 获取当前系统时间
     *
     * @return 时间
     */
    public static String getCurrentTimeDefaultFormat() {
        Date time = new Date();
        return defaultTimeFormat.format(time);
    }

    /**
     * 获取当前系统时间
     *
     * @return 时间
     */
    public static String getCurrentTimeOnlyDefaultFormat() {
        Date time = new Date();
        return defaultTimeOnlyFormat.format(time);
    }

    /**
     * 转换毫秒为时间
     *
     * @return 时间
     */
    public static String getTimeDefaultFormat(long ms) {
        Date time = new Date(ms);
        return defaultTimeFormat.format(time);
    }

    /**
     * 转换Date为时间
     *
     * @return 时间
     */
    public static String getTimeDefaultFormat(Date time) {
        if (time == null) {
            return null;
        }
        return defaultTimeFormat.format(time);
    }

    /**
     * 转换Date为时间
     *
     * @return 时间
     */
    public static String getTimeOnlyDefaultFormat(Date time) {
        if (time == null) {
            return null;
        }
        return defaultTimeOnlyFormat.format(time);
    }

    /**
     * 转换Date为时间
     *
     * @return 时间
     */
    public static String getDateTimeDefaultFormat(Date time) {
        if (time == null) {
            return null;
        }
        return defaultDateTimeFormat.format(time);
    }
	
    /**
     * 将标准格式时间转化为日期
     *
     * @param str 标准格式时间
     * @return 日期
     */
    public static Date getDateTimeDefaultFormat(String str) {
        try {
            return defaultDateTimeFormat.parse(str);
        } catch (ParseException e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * 将标准格式时间转化为日期
     *
     * @param str 标准格式时间
     * @return 日期
     */
    public static Date getDateFromDefaultFormat(String str) {
        try {
            return defaultTimeFormat.parse(str);
        } catch (ParseException e) {
            throw new DevelopmentException(e);
        }
    }

    /**
     * 将标准格式时间转化为Long
     *
     * @param str 标准格式时间
     * @return 日期
     */
    public static long getLongFromDefaultFormat(String str) {
        return getDateFromDefaultFormat(str).getTime();
    }

    /**
     * 检查是否符合标准时间格式
     *
     * @param str 时间
     * @return 是否符合
     */
    public static boolean checkDefaultFormat(String str) {
        try {
            defaultTimeFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 获取当前系统时间的前n天
     *
     * @return 时间
     */
    public static long getCurrentTimeBefforeDays(int days) {
        Date today =  new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        return  calendar.getTime().getTime();
    }

}
