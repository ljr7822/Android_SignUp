package com.example.iwen.factory.presenter.user;

import android.content.Context;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.model.db.account.UserRspModel;

/**
 * 完善用户信息的契约
 * @author : iwen大大怪
 * create : 12-17 017 10:31
 */
public interface UpdateInfoContract {
    // 更新
    interface Presenter extends BaseContract.Presenter{
        void update(Context context,String photoFilePath, String department,
                    String name, String password, String phone, int isMan);
    }
    // 回调成功
    interface View extends BaseContract.View<Presenter>{
        void UpdateSuccess(UserRspModel userRspModel);
    }
}
