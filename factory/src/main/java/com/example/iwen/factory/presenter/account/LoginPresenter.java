package com.example.iwen.factory.presenter.account;

import android.os.AsyncTask;
import android.service.autofill.UserData;

import androidx.room.Room;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.common.utils.HashUtil;
import com.example.iwen.factory.R;
import com.example.iwen.factory.data.database.userDataBase.UserDao;
import com.example.iwen.factory.data.database.userDataBase.UserDataBase;
import com.example.iwen.factory.data.database.userDataBase.UserDataModel;
import com.example.iwen.factory.data.helper.AccountHelper;
import com.example.iwen.factory.model.api.account.LoginModel;
import com.example.iwen.factory.model.db.account.LoginRspModel;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * 登录部分实现类
 *
 * @author : Iwen大大怪
 * create : 11-18 018 9:31
 */
public class LoginPresenter
        extends BasePresenter<LoginContract.View>
        implements LoginContract.Presenter, DataSource.Callback<LoginRspModel> {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    /**
     * 开始登录
     *
     * @param workId   工号
     * @param password 密码
     * @param deviceId 设备识别码
     */
    @Override
    public void login(String workId, String password, String deviceId) {
        // 调用start方法，默认启动了Loading
        start();
        // 得到View接口
        LoginContract.View view = getView();
        // 校验
        if (workId.length() < 6) {
            // 工号号不合法
            view.showError(R.string.data_account_login_invalid_parameter_work_id);
        } else if (password.length() < 6) {
            // 密码需要大于六位
            view.showError(R.string.data_account_register_invalid_parameter_password);
        } else {
            // 进行网络请求
            // 构造model进行请求调用
            String passwordMd5 = HashUtil.getMD5String(password);
            LoginModel model = new LoginModel(workId, passwordMd5, deviceId);
            // 进行网络请求，并设置回送接口为自己
            AccountHelper.login(model, this);
        }
    }

    @Override
    public void onDataLoaded(final LoginRspModel loginRspModel) {
        // 当网络请求成功，登录好了，回送一个用户信息来
        // 告知界面登录成功
        final LoginContract.View view = getView();
        if (view == null)
            return;
        // 此时是网络回送回来的，并不保证处于主线程状态
        // 强制进行线程切换
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 调用主界面注册成功
                view.loginSuccess(loginRspModel);
            }
        });
    }

    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知登录失败
        final LoginContract.View view = getView();
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
}
