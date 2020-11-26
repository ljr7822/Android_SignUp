package com.example.iwen.factory.presenter.location;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.presenter.account.LoginContract;

/**
 * @author : iwen大大怪
 * create : 11-26 026 12:08
 */
public interface LocationContract {
    interface View extends BaseContract.View<LoginContract.Presenter>{
        // 获取成功
        void getSuccess();
    }

    interface Presenter extends BaseContract.Presenter {
        // 发起一个获取请求
        void getPerson(String workId, String date);
    }
}
