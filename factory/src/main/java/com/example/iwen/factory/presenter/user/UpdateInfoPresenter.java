package com.example.iwen.factory.presenter.user;

import android.content.Context;
import android.text.TextUtils;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.factory.Factory;
import com.example.iwen.factory.R;
import com.example.iwen.factory.data.helper.UserHelper;
import com.example.iwen.factory.model.api.user.UserUpdateModel;
import com.example.iwen.factory.model.db.User;
import com.example.iwen.factory.model.db.account.UserRspModel;
import com.example.iwen.factory.net.UploadHelper;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * 更新用户信息的具体逻辑操作(p层)
 *
 * @author : iwen大大怪
 * create : 12-17 017 10:32
 */
public class UpdateInfoPresenter
        extends BasePresenter<UpdateInfoContract.View>
        implements UpdateInfoContract.Presenter, DataSource.Callback<UserRspModel> {

    public UpdateInfoPresenter(UpdateInfoContract.View view) {
        super(view);
    }

    @Override
    public void update(Context context,final String photoFilePath, final String department,
                       final String name, final String phone, final String password,
                       final int isMan) {
        start();
        final UpdateInfoContract.View view = getView();
        // 工号、mac设备号在内部获取并进行传入，不需要直接外部传入
        final String workId = (String) SPUtils.get(context,"workId","10010001");
        // mac在登录时已经写入数据库
        final String mac = (String) SPUtils.get(context,"DeviceId","10010001");
        // 校验数据
        if (TextUtils.isEmpty(photoFilePath) ||
                TextUtils.isEmpty(department)||
                TextUtils.isEmpty(name)||
                TextUtils.isEmpty(phone)||
                TextUtils.isEmpty(password)) {
            view.showError(R.string.data_account_update_invalid_parameter);
        } else {
            // 头像上传
            Factory.runOnAsync(new Runnable() {
                @Override
                public void run() {
                    String url = UploadHelper.uploadAvatar(photoFilePath);
                    if (url == null) {
                        // 上传失败
                        view.showError(R.string.data_upload_error);
                    } else {
                        // 构建model
                        UserUpdateModel model =
                                new UserUpdateModel(workId,name,phone,photoFilePath,department,password,mac,isMan);
                        UserHelper.update(model,UpdateInfoPresenter.this);
                    }
                }
            });
        }
    }

    @Override
    public void onDataLoaded(UserRspModel userRspModel) {
        // 告知界面更新成功
        final UpdateInfoContract.View view = getView();
        if (view == null)
            return;
        // 该方法是从网络回调的，需要回主线程更新UI
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                view.UpdateSuccess();
            }
        });
    }

    @Override
    public void onDataNotAvailable(final int strRes) {
        final UpdateInfoContract.View view = getView();
        if (view == null)
            return;
        // 该方法是从网络回调的，需要回主线程更新UI
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 告知界面更新失败，显示错误
                view.showError(strRes);
            }
        });
    }
}
