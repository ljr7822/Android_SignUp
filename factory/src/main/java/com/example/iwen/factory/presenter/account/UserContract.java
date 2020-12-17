package com.example.iwen.factory.presenter.account;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.model.db.account.UserRspModel;
import com.example.iwen.factory.model.db.sign.SignRspModel;

/**
 * 个人中心契约
 *
 * @author : iwen大大怪
 * create : 12-10 010 18:40
 */
public interface UserContract {
    interface View extends BaseContract.View<UserContract.Presenter> {
        // 获取成功,返回一个UserCard
        void userCardSuccess(UserRspModel userRspModel);
    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * 获取用户信息
         *
         * @param workId 工号
         */
        void userCard(String workId);
    }
}
