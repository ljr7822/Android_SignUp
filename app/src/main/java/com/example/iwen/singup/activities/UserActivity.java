package com.example.iwen.singup.activities;

import android.annotation.SuppressLint;
import android.content.Intent;

import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.singup.R;
import com.example.iwen.singup.fragment.user.UpdateInfoFragment;

/**
 * 用户的activity
 */
public class UserActivity extends BaseActivity {
    private BaseFragment mFragment;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 初始化Fragment
        mFragment = new UpdateInfoFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container,mFragment)
                .commit();
    }

    // 收到从Activity传过来的回调，取出其中的值进行图片加载
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mFragment.onActivityResult(requestCode,resultCode,data);
    }
}