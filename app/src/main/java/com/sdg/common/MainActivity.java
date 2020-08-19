package com.sdg.common;

import com.sdg.core.mvp.BaseActivity;
import com.sdg.common.R;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        showShort("加载成功");
    }

    @Override
    protected void refreshTodo() {

    }
}
