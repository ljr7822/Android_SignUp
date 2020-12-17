package com.example.iwen.factory.presenter.account;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.factory.Factory;
import com.example.iwen.factory.data.helper.UserHelper;
import com.example.iwen.factory.model.api.account.UserModel;
import com.example.iwen.factory.model.db.account.UserRspModel;
import com.example.iwen.factory.net.UploadHelper;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * 个人中心的p层
 *
 * @author : iwen大大怪
 * create : 12-10 010 18:43
 */
public class UserPresenter
        extends BasePresenter<UserContract.View>
        implements UserContract.Presenter, DataSource.Callback<UserRspModel> {
    public UserPresenter(UserContract.View view) {
        super(view);
    }

    @Override
    public void userCard( final String workId) {
        start();
        final UserContract.View view = getView();
        // 头像上传
        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                //String url = UploadHelper.uploadAvatar(photoFilePath);
                // 构造发起请求的数据模型
                UserModel userModel = new UserModel( workId);
                // 发起网络请求
                UserHelper.getUser(userModel, UserPresenter.this);
            }
        });
    }

    /**
     * 成功的网络回调
     *
     * @param userRspModel UserRspModel
     */
    @Override
    public void onDataLoaded(final UserRspModel userRspModel) {
        // 得到View接口
        final UserContract.View view = getView();
        if (view != null) {
            // 此时是从网络回送回来的，并不保证处于主线程状态
            // 进行线程切换
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    // 调用成功
                    view.userCardSuccess(userRspModel);
                }
            });
        }
    }

    /**
     * 失败的网络回调
     *
     * @param strRes 失败信息
     */
    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知网络失败
        // 得到View接口
        final UserContract.View view = getView();
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
