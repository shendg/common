package com.dimeno.core.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dimeno.core.utils.LoadingUtil;
import com.dimeno.core.utils.MyUtils;
import com.dimeno.core.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


/**
 * activity基类
 * @author sdg
 * 2020/4/8
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected Activity baseContext;
    protected T mPresenter;
    protected Bundle bundle;
    private LoadingUtil loadingUtil;
    private long mExitTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        bundle = savedInstanceState;
        baseContext = this;
        createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
//        setStatusBar();
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        MyUtils.fixInputMethodManagerLeak(this);//解决InputMethodManager内存泄露现象
    }

    /**
     * 布局
     * */
    protected abstract int getLayout();

    /**
     * presenter
     * */
    protected abstract void createPresenter();

    /**
     * 初始化控件
     * */
    protected abstract void initView();

    /**
     * 初始化数据
     * */
    protected abstract void initData();

    /**
     * 下拉刷新 如有需要及方法内添加刷新数据即可
     * */
    protected abstract void refreshTodo();

    /**
     * 绑定 smartRefreshLayout
     * @param smartRefreshLayout
     * desc:使用时在布局内用 SmartRefreshLayout 包裹住所需要刷新的列表
     * 需要在你的activity里面的initdata方法调用此方法
     * 默认1500ms
     * */
    //TODO 待迭代 可让用户自行设置刷新布局
    public void baseAcSmartRefresh(SmartRefreshLayout smartRefreshLayout) {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshTodo();
                refreshLayout.finishRefresh(1500);
            }
        });
    }

    /**
     * 绑定 smartRefreshLayout
     * @param smartRefreshLayout
     * desc:使用时在布局内用 SmartRefreshLayout 包裹住所需要刷新的列表
     * 需要在你的activity里面的initdata方法调用此方法
     * @param time 刷新的延时时间
     * */
    public void baseAcSmartRefresh(SmartRefreshLayout smartRefreshLayout, final int time) {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshTodo();
                refreshLayout.finishRefresh(time);
            }
        });
    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     * @param clz activity
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, Bundle dle) {
        gotoActivity(clz, false, dle);
    }

    /**
     * 打开一个Activity
     * @param clz activity
     * @param isCloseCurrentActivity 是否关闭当前activity
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    /**
     * 打开一个Activity
     * @param clz activity
     * @param isCloseCurrentActivity 是否关闭当前activity
     * @param ex 携带bundle数据
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(this, clz);
        if (ex != null)
            intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    /**
     * 关闭当前activity
     * */
    public void finishActivity() {
        baseContext.finish();
    }

    /**
     * 显示进度条
     */
    //TODO 添加handler内显示和停止动画 避免数据过大时动画卡顿
    public void showLoading() {
        if (loadingUtil == null) {
            loadingUtil = new LoadingUtil(this);
        }
        loadingUtil.show();
    }

    /**
     * 显示文本信息进度条
     */
    public void showTextLoading(String text) {
        if (loadingUtil == null) {
            loadingUtil = new LoadingUtil(this);
        }
        loadingUtil.setText(text);
        loadingUtil.show();
    }

    /**
     * 隐藏进度条
     */
    public void hideLoading() {
        if (loadingUtil != null) {
            loadingUtil.hide();
        }
    }

    /**
     * 退出app
     * @param intervalTime 两次点击的间隔时间
     * */
    public void exit(long intervalTime) {
        if ((System.currentTimeMillis() - mExitTime) > intervalTime) {
            showShort("再按一次退出应用");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 短吐司
     * @param msg 吐司内容
     * */
    public void showShort(String msg){
        ToastUtil.showShort(baseContext,msg);
    }

    /**
     * 长吐司
     * @param msg 吐司内容
     * */
    public void showLong(String msg){
        ToastUtil.showLong(baseContext,msg);
    }

}