package com.example.iwen.factory.presenter.account;

import androidx.annotation.StringRes;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.model.db.account.LoginRspModel;
import com.example.iwen.factory.model.db.account.UserRspModel;

/**
 * 登录部分逻辑接口
 *
 * @author : Iwen大大怪
 * create : 2020/11/16 20:10
 */
public interface LoginContract {
    interface View extends BaseContract.View<Presenter> {
        // 登录成功
        void loginSuccess(LoginRspModel loginRspModel);
    }

    interface Presenter extends BaseContract.Presenter {
        // 发起一个登录
        void login(String workId, String password, String deviceId);
    }
}
