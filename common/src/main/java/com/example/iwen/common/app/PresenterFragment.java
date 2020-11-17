package com.example.iwen.common.app;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.iwen.common.factory.presenter.BaseContract;

/**
 * 基础的 presenterFragment
 * author : Iwen大大怪
 * create : 2020/11/16 20:38
 */
public abstract class PresenterFragment<Presenter extends BaseContract.Presenter>
        extends BaseFragment
        implements BaseContract.View<Presenter> {
    protected Presenter mPresenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 在界面onAttach之后就开始触发
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
