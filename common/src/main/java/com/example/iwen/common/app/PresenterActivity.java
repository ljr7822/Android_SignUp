package com.example.iwen.common.app;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.iwen.common.factory.presenter.BaseContract;

/**
 * Presenter基类
 * @author : iwen大大怪
 * create : 11-29 029 22:01
 */
public abstract class PresenterActivity<Presenter extends BaseContract.Presenter>
        extends BaseActivity
        implements BaseContract.View<Presenter>{

    protected Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在界面onCreate之后就开始触发
        initPresenter();
    }

    /**
     * 初始化 Presenter
     * @return Presenter
     */
    protected abstract Presenter initPresenter();

    @Override
    public void showError(int str) {
        // 错误提示
        Application.showToast(str);
    }

    @Override
    public void showLoading() {
        // TODO 显示一个loading
    }

    @Override
    public void setPresenter(Presenter presenter) {
        // view中赋值Presenter
        mPresenter = presenter;
    }
}
