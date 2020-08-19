package com.dimeno.core.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast工具类
 * @author sdg
 * @creteTime 2020/4/8
 */
public class ToastUtil {

    /**
     * 短吐司
     * @param msg 吐司内容
     * */
    public static void showShort(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * 长吐司
     * @param msg 吐司内容
     * */
    public static void showLong(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
