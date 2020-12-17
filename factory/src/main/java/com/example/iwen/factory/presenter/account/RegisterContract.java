package com.example.iwen.factory.presenter.account;

import androidx.annotation.StringRes;

import com.example.iwen.common.factory.presenter.BaseContract;

/**
 * 注册部分逻辑接口
 * @author : Iwen大大怪
 * create : 2020/11/16 20:10
 */
public interface RegisterContract {
    interface View extends BaseContract.View<Presenter>{
        // 注册成功
        void registerSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        // 发起一个注册
        void register(String phone, String name, String password);
        // 检查手机号是否正确
        boolean checkMobile(String phone);
    }
}
