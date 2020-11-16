package com.example.iwen.singup.activities;

import android.content.Context;
import android.content.Intent;

import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.singup.R;
import com.example.iwen.singup.fragment.account.AccountTrigger;
import com.example.iwen.singup.fragment.account.LoginFragment;
import com.example.iwen.singup.fragment.account.RegisterFragment;

/**
 * author : Iwen大大怪
 * create : 2020/11/15 9:27
 */
public class AccountActivity extends BaseActivity implements AccountTrigger {
    private BaseFragment mFragment;
    private BaseFragment mLoginFragment;
    private BaseFragment mRegisterFragment;

    /**
     * 账户Activity的显示入口
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 初始化Fragment
        mFragment = mLoginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mFragment)
                .commit();
    }

    /**
     * 切换具体的fragment
     */
    @Override
    public void triggerView() {
        BaseFragment fragment;
        if (mFragment == mLoginFragment) {
            if (mRegisterFragment == null) {
                // 默认情况下为null
                // 第一次之后就不为空
                mRegisterFragment = new RegisterFragment();
            }
            fragment = mRegisterFragment;
        } else {
            // 因为默认清况已经赋值
            fragment = mLoginFragment;
        }
        // 重新赋值当前正在显示的Fragment
        mFragment = fragment;
        // 切换显示
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lay_container, fragment)
                .commit();
    }
}
