package com.dimeno.core.utils;

import com.socks.library.KLog;

/**
 * log工具类
 * @author sdg
 * @creteTime 2020/4/8
 * @desc 暂定使用klog 统一使用此工具类打印日志，即使后面需要切换也方便切换
 */
public class LogUtil {

    /**
     * d log
     * @param msg
     * */
    public static void d(String msg){
        KLog.d(msg);
    }

    /**
     * d log
     * @param msg
     * @param tag
     * */
    public static void d(String tag,String msg){
        KLog.d(tag,msg);
    }

    /**
     * i log
     * @param msg
     * */
    public static void i(String msg){
        KLog.i(msg);
    }

    /**
     * i log
     * @param msg
     * @param tag
     * */
    public static void i(String tag,String msg){
        KLog.i(tag,msg);
    }

    /**
     * w log
     * @param msg
     * */
    public static void w(String msg){
        KLog.w(msg);
    }

    /**
     * w log
     * @param msg
     * @param tag
     * */
    public static void w(String tag,String msg){
        KLog.w(tag,msg);
    }

    /**
     * e log
     * @param msg
     * */
    public static void e(String msg){
        KLog.e(msg);
    }

    /**
     * e log
     * @param msg
     * @param tag
     * */
    public static void e(String tag,String msg){
        KLog.e(tag,msg);
    }

    /**
     * a log
     * @param msg
     * */
    public static void a(String msg){
        KLog.a(msg);
    }

    /**
     * a log
     * @param msg
     * @param tag
     * */
    public static void a(String tag,String msg){
        KLog.a(tag,msg);
    }

}
