package com.sdg.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    /**
     * 获取当前时间并格式化为 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = System.currentTimeMillis();
        String timestr = df.format(new Date(l));
        return timestr;
    }

    /**
     * 获取年
     *
     * @return
     */
    public static String getYear() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.YEAR) + "";
    }

    /**
     * 获取月
     *
     * @return
     */
    public static String getMonth() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MONTH) + 1 + "";
    }

    /**
     * 获取日
     *
     * @return
     */
    public static int getDay() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.DATE);
    }

    /**
     * 获取时
     *
     * @return
     */
    public static int getHour() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.HOUR);
    }

    /**
     * 获取分
     *
     * @return
     */
    public static int getMinute() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MINUTE);

    }

    /**
     * 获取当前时间的时间戳
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDate2Bigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = dt1.getTime();
        long time2 = dt2.getTime();
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    //小于当前日期
    public static boolean isLessCurrentDate(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time1 = dt1.getTime();
        long time2 = dt2.getTime();

        if (dt1.getTime() < dt2.getTime()) {
            isBigger = true;
        } else {
            isBigger = false;
        }
        return isBigger;
    }

}
