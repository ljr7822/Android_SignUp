package com.example.iwen.factory.presenter.account;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.factory.data.helper.UserHelper;
import com.example.iwen.factory.model.api.account.UserModel;
import com.example.iwen.factory.model.db.account.PersonalRecordRspModel;
import com.example.iwen.factory.presenter.notice.NoticeContract;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.List;

/**
 * 获取个人打卡记录列表的p层
 *
 * @author : iwen大大怪
 * create : 12-10 010 21:46
 */
public class PersonalRecordPresenter
        extends BasePresenter<PersonalRecordContract.View>
        implements PersonalRecordContract.Presenter, DataSource.Callback<List<PersonalRecordRspModel>> {

    public PersonalRecordPresenter(PersonalRecordContract.View view) {
        super(view);
    }

    /**
     * 具体实现
     *
     * @param workId 工号
     */
    @Override
    public void getPersonalRecord(String workId) {
        start();
        PersonalRecordContract.View view = getView();
        // 构造发起请求的数据模型
        UserModel userModel = new UserModel(workId);
        // TODO 发起网络请求
        UserHelper.getPersonalRecord(userModel,this);
    }

    /**
     * 网络请求成功的回调
     *
     * @param personalRecordRspModelList List<PersonalRecordRspModel>
     */
    @Override
    public void onDataLoaded(final List<PersonalRecordRspModel> personalRecordRspModelList) {
        // 得到View接口
        final PersonalRecordContract.View view = getView();
        if (view != null) {
            // 此时是从网络回送回来的，并不保证处于主线程状态
            // 进行线程切换
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    // 调用成功
                    view.getPersonalRecordSuccess(personalRecordRspModelList);
                }
            });
        }
    }

    /**
     * 网络请求失败的回调
     *
     * @param strRes 提示
     */
    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知网络失败
        // 得到View接口
        final PersonalRecordContract.View view = getView();
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
