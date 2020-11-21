package com.example.iwen.singup.fragment.account;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.factory.presenter.account.RegisterContract;
import com.example.iwen.factory.presenter.account.RegisterPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.activities.MainActivity;

import net.qiujuer.genius.ui.widget.Loading;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册的Fragment
 */
public class RegisterFragment
        extends PresenterFragment<RegisterContract.Presenter>
        implements RegisterContract.View {
    private AccountTrigger mAccountTrigger;
    // 手机号
    @BindView(R.id.edt_phone)
    EditText mPhone;
    // 姓名
    @BindView(R.id.edt_name)
    EditText mName;
    // 密码
    @BindView(R.id.edt_password)
    EditText mPassword;
    // 加载
    @BindView(R.id.loading)
    Loading mLoading;
    // 按钮
    @BindView(R.id.btn_submit)
    Button mSubmit;


    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * 正真的切换两个Fragment显示
     * @param context 上下文
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected RegisterContract.Presenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_register;
    }

    // 注册按钮点击事件
    @OnClick(R.id.btn_submit)
    void onSubmitClick() {
        String phone = mPhone.getText().toString();
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        // 调用p层进行注册
        // TODO 注册网络请求逻辑
        // mPresenter.register(phone, name, password);
    }

    // 跳转至登录界面
    @OnClick(R.id.tv_go_login)
    void onShowLoginClick(){
        // 让AccountTrigger进行界面切换
        mAccountTrigger.triggerView();
    }

    @Override
    public void showError(int str) {
        super.showError(str);
        // 当提示需要显示错误的时候触发，一定是结束了
        // 停止loading
        mLoading.stop();
        // 让控件可以输入
        mPhone.setEnabled(true);
        mName.setEnabled(true);
        mPassword.setEnabled(true);
        // 提交按钮可以继续点击
        mSubmit.setEnabled(true);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        // 正在进行时，界面不可操作
        // 开始loading
        mLoading.start();
        // 让控件不可以输入
        mPhone.setEnabled(false);
        mName.setEnabled(false);
        mPassword.setEnabled(false);
        // 提交按钮不可以继续点击
        mSubmit.setEnabled(false);
    }

    /**
     * 注册成功的回调
     */
    @Override
    public void registerSuccess() {
        // 注册成功，这时账户已经登录，进行跳转到MainActivity界面
        MainActivity.show(getContext());
        // 关闭当前界面
        getActivity().finish();
    }
}