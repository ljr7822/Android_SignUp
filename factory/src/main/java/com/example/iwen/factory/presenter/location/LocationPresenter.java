package com.example.iwen.factory.presenter.location;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.factory.data.helper.LocationHelper;
import com.example.iwen.factory.model.api.Location.GetPersonLocationModel;
import com.example.iwen.factory.model.db.RspListModel;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * 定位的部分实现类
 *
 * @author : iwen大大怪
 * create : 11-26 026 12:06
 */
public class LocationPresenter extends BasePresenter<LocationContract.View>
        implements LocationContract.Presenter, DataSource.Callback<RspListModel> {

    public LocationPresenter(LocationContract.View view) {
        super(view);
    }

    @Override
    public void onDataLoaded(RspListModel rspListModel) {
        // 当网络请求成功，登录好了，回送一个用户信息来
        // 告知界面登录成功
        final LocationContract.View view = getView();
        if (view == null)
            return;
        // 此时是网络回送回来的，并不保证处于主线程状态
        // 强制进行线程切换
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 调用主界面注册成功
                view.getSuccess();
            }
        });
    }

    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知登录失败
        final LocationContract.View view = getView();
        if (view == null)
            return;
        // 此时是网络回送回来的，并不保证处于主线程状态
        // 强制进行线程切换
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 调用主界面注册失败显示错误
                view.showError(strRes);
            }
        });
    }

    /**
     * 开始获取
     *
     * @param workId 员工id
     * @param date   时间
     */
    @Override
    public void getPerson(String workId, String date) {
        // 得到View接口
        LocationContract.View view = getView();
//        DateTimeUtil dateTimeUtil = new DateTimeUtil();
//        String time = dateTimeUtil.getTime();
//        String timedate = time.split(" ")[0].trim();
        // 构造model进行请求调用
        GetPersonLocationModel getPersonLocationModel = new GetPersonLocationModel(workId, date);
        // 进行网络请求，并设置回送接口为自己
        LocationHelper.getPerson(getPersonLocationModel, this);
    }
}
