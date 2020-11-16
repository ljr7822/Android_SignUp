package com.example.iwen.singup.fragment.account;

import android.content.Context;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.singup.R;

/**
 * 登录的Fragment
 */
public class LoginFragment extends BaseFragment {
    private AccountTrigger mAccountTrigger;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onResume() {
        super.onResume();
        // 进行一次切换，默认切换为注册界面
        mAccountTrigger.triggerView();
    }
}