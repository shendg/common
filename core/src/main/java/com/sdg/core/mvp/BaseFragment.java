package com.sdg.core.mvp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sdg.core.utils.LoadingUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * fragment 基类
 * @author sdg
 * 2020/4/8
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {
    protected View rootView;
    protected T mPresenter;
    protected Context baseContext;
    private LoadingUtil loadingUtil;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        baseContext = getActivity();
        if (rootView == null) rootView = inflater.inflate(getLayout(), container, false);
        createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        initData();
        return rootView;
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
     * desc:使用时在布局内用SmartRefreshLayout 包裹住所需要刷新的列表
     * 需要在你的activity里面的initdata方法调用此方法
     * 默认1500ms
     * */
    public void baseFrSmartRefresh(SmartRefreshLayout smartRefreshLayout) {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshTodo();
                refreshLayout.finishRefresh(1500);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Nullable
    @Override
    public Context getContext() {
        return this.getActivity();
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    /*
     * Called when the fragment attaches to the context
     */
    protected void onAttachToContext(Context context) {
        //do something
    }

    @TargetApi(23)
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);
    }

    /**
     * 懒加载过
     */
    private boolean isLazyLoaded;

    private boolean isPrepared;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPrepared = true;
        //只有Fragment onCreateView好了，
        //另外这里调用一次lazyLoad(）
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    /**
     * 调用懒加载
     */

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = true;
        } else {
            if (!getUserVisibleHint() && isPrepared) {
                onInvisible();
            }
        }

    }

    /**
     * 可见
     */
    protected void onLazyLoad() {

    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    //打开一个Activity 默认 不关闭当前activity
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle ex) {
        Intent intent = new Intent(baseContext, clz);
        if (ex != null)
            intent.putExtras(ex);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            ((BaseActivity) baseContext).finish();
        }
    }

    /**
     * 显示进度条
     */
    public void showLoading() {
        if (loadingUtil == null) {
            loadingUtil = new LoadingUtil(getActivity());
        }
        loadingUtil.show();
    }

    /**
     * 显示文本信息进度条
     */
    public void showTextLoading(String text) {
        if (loadingUtil == null) {
            loadingUtil = new LoadingUtil(getActivity());
        }
        loadingUtil.setText(text);
        loadingUtil.show();
    }

}
