package com.harry.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: wangruirui
 * @date: 2018/2/6
 * @description: 日期工具类
 */
public final class DateUtil2 {

    /**
     * 线程安全的日期格式对象
     */
    private static final ThreadLocal<DateFormat> DATE_FORMAT = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            // 完整日期
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

    };

    /**
     * 线程安全的日期格式对象
     */
    private static final ThreadLocal<DateFormat> YMD = new ThreadLocal<DateFormat>() {

        @Override
        protected DateFormat initialValue() {
            // 年月日
            return new SimpleDateFormat("yyyy-MM-dd");
        }

    };

    /**
     * 格式化完整日期
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static final String formatDate(Date date) {
        return DATE_FORMAT.get().format(date);
    }

    /**
     * 格式化年月日
     *
     * @param date
     * @return yyyy-MM-dd格式的字符串
     */
    public static final String formatYMD(Date date) {
        return YMD.get().format(date);
    }

    /**
     * 获取指定日期0点的字符串
     *
     * @param date
     * @return 返回示例:2017-12-23 00:00:00
     */
    public static final String getZeroPointStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return formatDate(calendar.getTime());
    }

    /**
     * 获取指定日期末点的字符串
     *
     * @param date
     * @return 返回示例:2017-12-23 23:59:59
     */
    public static final String getLastPointStr(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return formatDate(calendar.getTime());
    }

    /**
     * 获取指定日期的0点的毫秒数
     *
     * @param date
     * @return
     */
    public static final long getZeroPointMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime().getTime();
    }

    /**
     * 获取指定日期的末点的毫秒数
     *
     * @param date
     * @return
     */
    public static final long getLastPointMillisecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime().getTime();
    }

    /**
     * 获取当前月的第一天
     */
    public static String getThisMonth () {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MONTH, 0);

        calendar.set(Calendar.DAY_OF_MONTH, 1);


        StringBuffer time = new StringBuffer();

        String first = YMD.get().format(calendar.getTime());

        time.append(first).append(" ").append("00:00:00");

        return time.toString();

    }

    /**
     * 获取当前时间上月第一天
     * @param date
     * @return
     * @throws ParseException
     */
    public static String getLastMonth (Date date) {
        Calendar cal_1=Calendar.getInstance();
        cal_1.add(Calendar.MONTH, -1);

        //设置为1号,当前日期既为本月第一天
        cal_1.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = DATE_FORMAT.get().format(cal_1.getTime());

        return firstDay;
    }

    /**
     * 获取昨天凌晨时间
     * @return
     */
    public static String getYesterdayStart () {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);

        date = calendar.getTime();

        StringBuffer time = new StringBuffer();

        String dateString  = YMD.get().format(date);

        time.append(dateString).append(" ").append("00:00:00");

        return time.toString();

    }

    /**
     * 获取昨天最后时间
     * @return
     */
    public static String getYesterdayEnd () {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);

        date = calendar.getTime();


        StringBuffer time = new StringBuffer();

        String dateString  = YMD.get().format(date);

        time.append(dateString).append(" ").append("23:59:59");

        return time.toString();
    }

    /**
     * 获取昨天最后时间
     * @return
     */
    public static String getYesterdayMark () {

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);

        date = calendar.getTime();

        StringBuffer time = new StringBuffer();

        String dateString  = YMD.get().format(date);

        time.append(dateString).append(" ").append("23:00:00");

        return time.toString();
    }


    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(formatDate(now));
        System.out.println(formatYMD(now));
        System.out.println(getZeroPointStr(now));
        System.out.println(getLastPointStr(now));
        System.out.println(getZeroPointMillisecond(now));
        System.out.println(getLastPointMillisecond(now));
    }
}
