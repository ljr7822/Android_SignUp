package com.example.iwen.factory.presenter.notice;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.model.db.notice.NoticeRspModel;

import java.util.List;

/**
 * 获取公告列表的契约
 *
 * @author : iwen大大怪
 * create : 12-1 001 18:17
 */
public interface NoticeContract {
    interface View extends BaseContract.View<NoticeContract.Presenter> {
        // 获取成功,返回一个SignRspModel
        void getNoticeSuccess(List<NoticeRspModel> noticeRspModel);
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 发起获取请求
         *
         * @param departmentId 部门id
         */
        void getNotice(String departmentId);
    }
}
