package com.example.iwen.common.factory.presenter;

import androidx.annotation.StringRes;

/**
 * Mvp模式公用的基本契约
 * author : Iwen大大怪
 * create : 2020/11/16 20:27
 */
public interface BaseContract {
    interface View<T extends Presenter>{
        // 公共：显示一个字符串错误
        void showError(@StringRes int str);
        // 公共：显示一个进度条
        void showLoading();
        // 支持设置一个Presenter
        void setPresenter(T presenter);
    }

    interface Presenter{
        // 公用的开始触发
        void start();
        // 公用的销毁触发
        void destroy();
    }
}
