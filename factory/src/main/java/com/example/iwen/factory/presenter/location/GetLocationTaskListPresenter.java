package com.example.iwen.factory.presenter.location;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.factory.data.helper.LocationHelper;
import com.example.iwen.factory.model.api.Location.GetPersonLocationModel;
import com.example.iwen.factory.model.db.location.LocationTaskList;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.List;

/**
 * 获取定位任务列表的presenter
 *
 * @author : iwen大大怪
 * create : 11-28 028 4:06
 */
public class GetLocationTaskListPresenter
        extends BasePresenter<GetLocationTaskListContract.View>
        implements GetLocationTaskListContract.Presenter,
                    DataSource.Callback<List<LocationTaskList>> {

    public GetLocationTaskListPresenter(GetLocationTaskListContract.View view) {
        super(view);
    }

    @Override
    public void getLocationTaskList(String workId, String date) {
        // 调用开始方法，在start中默认启动了Loading
        start();
        // 得到View接口
        GetLocationTaskListContract.View view = getView();
        // 构造Model，进行请求调用
        GetPersonLocationModel getPersonLocationModel = new GetPersonLocationModel(workId);
        // 进行网络请求，并设置回调接口为自己
        LocationHelper.getTaskList(getPersonLocationModel, this);
    }

    // 成功网络回调
    @Override
    public void onDataLoaded(final List<LocationTaskList> locationTaskLists) {
        // 得到View接口
        final GetLocationTaskListContract.View view = getView();
        if (view != null) {
            // 此时是从网络回送回来的，并不保证处于主线程状态
            // 进行线程切换
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    // 调用成功
                    view.getLocationTaskListSuccess(locationTaskLists);
                }
            });
        }
    }

    // 失败网络回调
    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知网络失败
        // 得到View接口
        final GetLocationTaskListContract.View view = getView();
        if (view == null)
            return;
        // 此时是从网络回送回来的，并不保证处于主线程状态
        // 进行线程切换
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 网络失败
                view.showError(strRes);
            }
        });
    }
}