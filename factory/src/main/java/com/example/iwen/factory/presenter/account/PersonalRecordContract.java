package com.example.iwen.factory.presenter.account;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.model.db.account.PersonalRecordRspModel;

import java.util.List;

/**
 * 获取个人打卡记录的契约
 *
 * @author : iwen大大怪
 * create : 12-10 010 21:47
 */
public interface PersonalRecordContract {
    interface View extends BaseContract.View<PersonalRecordContract.Presenter> {
        // 获取成功,返回一个UserCard
        void getPersonalRecordSuccess(List<PersonalRecordRspModel> personalRecordRspModelList);
    }

    interface Presenter extends BaseContract.Presenter {

        /**
         * 获取个人打卡记录
         *
         * @param workId 工号
         */
        void getPersonalRecord(String workId);
    }
}
