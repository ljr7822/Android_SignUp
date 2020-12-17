package com.example.iwen.factory.presenter.notice;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.factory.data.helper.NoticeHelper;
import com.example.iwen.factory.model.api.notice.NoticeModel;
import com.example.iwen.factory.model.db.notice.NoticeRspModel;
import com.example.iwen.factory.presenter.location.GetLocationTaskListContract;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.List;

/**
 * @author : iwen大大怪
 * create : 12-1 001 18:20
 */
public class NoticePresenter
        extends BasePresenter<NoticeContract.View>
        implements NoticeContract.Presenter, DataSource.Callback<List<NoticeRspModel>> {

    public NoticePresenter(NoticeContract.View view) {
        super(view);
    }

    @Override
    public void getNotice(String departmentId) {
        // 调用开始方法，在start中默认启动了Loading
        start();
        // 得到View接口
        NoticeContract.View view = getView();
        // 构造Model，进行请求调用
        NoticeModel noticeModel = new NoticeModel();
        // 进行网络请求，并设置回调接口为自己
        NoticeHelper.getNoticeList(noticeModel,this);
    }

    // 成功网络回调
    @Override
    public void onDataLoaded(final List<NoticeRspModel> noticeRspModels) {
        // 得到View接口
        final NoticeContract.View view = getView();
        if (view != null) {
            // 此时是从网络回送回来的，并不保证处于主线程状态
            // 进行线程切换
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    // 调用成功
                    view.getNoticeSuccess(noticeRspModels);
                }
            });
        }
    }

    // 失败网络回调
    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知网络失败
        // 得到View接口
        final NoticeContract.View view = getView();
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
