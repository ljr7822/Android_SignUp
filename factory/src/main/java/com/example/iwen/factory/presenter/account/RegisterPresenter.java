package com.example.iwen.factory.presenter.account;

import android.text.TextUtils;

import com.example.iwen.common.Common;
import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.factory.R;
import com.example.iwen.factory.data.helper.AccountHelper;
import com.example.iwen.factory.model.api.account.RegisterModel;
import com.example.iwen.factory.model.db.User;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.regex.Pattern;

/**
 * 注册部分实现类
 * author : Iwen大大怪
 * create : 2020/11/16 20:17
 */
public class RegisterPresenter
        extends BasePresenter<RegisterContract.View>
        implements RegisterContract.Presenter , DataSource.Callback<User> {

    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    /**
     * 开始注册
     *
     * @param phone    手机号
     * @param name     姓名
     * @param password 密码
     */
    @Override
    public void register(String phone, String name, String password) {
        // 调用start方法，默认启动了Loading
        start();
        // 得到View接口
        RegisterContract.View view = getView();
        // 校验
        if (!checkMobile(phone)) {
            // 手机号不合法
            view.showError(R.string.data_account_register_invalid_parameter_mobile);
        } else if (name.length() < 2) {
            // 姓名小于两位
            view.showError(R.string.data_account_register_invalid_parameter_name);
        } else if (password.length() < 6) {
            // 密码需要大于六位
            view.showError(R.string.data_account_register_invalid_parameter_password);
        } else {
            // 进行网络请求

            // 构造model进行请求调用
            RegisterModel model = new RegisterModel(phone, password, name);
            // 进行网络请求，并设置回送接口为自己
            AccountHelper.register(model,this);
        }
    }

    /**
     * 检测手机号是否合法
     *
     * @param phone 手机号
     * @return true时合法
     */
    @Override
    public boolean checkMobile(String phone) {
        // 手机号不为空，并且满足相应格式
        return !TextUtils.isEmpty(phone) && Pattern.matches(Common.Constance.REGEX_MOBILE, phone);
    }

    @Override
    public void onDataLoaded(User user) {
        // 当网络请求成功，注册好了，回送一个用户信息来
        // 告知界面注册成功
        final RegisterContract.View view = getView();
        if (view==null)
            return;
        // 此时是网络回送回来的，并不保证处于主线程状态
        // 强制进行线程切换
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 调用主界面注册成功
                view.registerSuccess();
            }
        });
    }

    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知注册失败
        final RegisterContract.View view = getView();
        if (view==null)
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
