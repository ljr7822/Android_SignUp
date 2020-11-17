package com.example.iwen.factory.presenter.account;

import androidx.annotation.StringRes;

import com.example.iwen.common.factory.presenter.BaseContract;

/**
 * 登录部分逻辑
 * author : Iwen大大怪
 * create : 2020/11/16 20:10
 */
public interface LoginContract {
    interface View extends BaseContract.View<Presenter>{
        // 登录成功
        void loginSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        // 发起一个登录
        void login(String phone, String password);
    }
}
