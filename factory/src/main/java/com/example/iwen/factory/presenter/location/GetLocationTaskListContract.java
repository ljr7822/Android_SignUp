package com.example.iwen.factory.presenter.location;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.model.db.location.LocationTaskList;

import java.util.List;

/**
 * 获取定位列表的Contract
 *
 * @author : iwen大大怪
 * create : 11-27 027 22:25
 */
public interface GetLocationTaskListContract {
    interface View extends BaseContract.View<Presenter> {
        // 获取成功,返回一个list列表
        void getLocationTaskListSuccess(List<LocationTaskList> locationTaskLists);
    }

    interface Presenter extends BaseContract.Presenter{
        // 发起请求获取
        void getLocationTaskList(String workId, String date);
    }
}
