package com.example.iwen.factory.presenter.sign;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.model.db.sign.InSignRspModel;
import com.example.iwen.factory.model.db.sign.SignRspModel;

/**
 * @author : iwen大大怪
 * create : 2020-12-22 13:41
 */
public interface InSignContract {
    interface View extends BaseContract.View<InSignContract.Presenter> {
        // 获取成功,返回一个SignRspModel
        void InSignSuccess(InSignRspModel inSignRspModel);

        // 签到失败，返回一个
        void InSignFailure(int failureMsg);
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 发起签到请求
         * @param collectId 记录id
         */
        void InSign(String collectId);
    }
}
