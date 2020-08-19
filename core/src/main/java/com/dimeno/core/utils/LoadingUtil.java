package com.dimeno.core.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dimeno.core.R;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * @author sdg
 * 2020/4/3
 */
public class LoadingUtil {
    private Dialog pd;
    private final TextView tv_progress;
    private final AVLoadingIndicatorView loading;

    public LoadingUtil(Activity activity) {
        pd = new Dialog(activity, com.dimeno.core.R.style.dialog);
        View view = LayoutInflater.from(activity).inflate(
                com.dimeno.core.R.layout.loading, null);
        tv_progress = view.findViewById(R.id.tv_progress);
        loading = view.findViewById(R.id.loading);
        pd.setContentView(view);
        pd.setCancelable(true);
    }

    /**
     * 显示进度条
     */
    public void show() {
        if (pd != null) {
            pd.show();
        }
    }

    /**
     * 关闭进度条
     */
    public void hide() {
        if (pd != null) {
            pd.dismiss();
        }
    }

    /**
     * 设置加载的文本信息
     * @param text 文本信息
     * */
    public void setText(String text) {
        tv_progress.setText(text);
    }

    //TODO willdo 添加加载动图设置

}
