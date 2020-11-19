package com.example.iwen.singup;

import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.singup.activities.AccountActivity;
import com.example.iwen.singup.activities.MainActivity;
import com.example.iwen.singup.fragment.assist.PermissionsFragment;

/**
 * 启动页面
 * author : Iwen大大怪
 * create : 2020/11/16 9:59
 */
public class LaunchActivity extends BaseActivity {
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionsFragment.haveAll(this,getSupportFragmentManager())){
            // 进入注册登录界面
            AccountActivity.show(this);
            // MainActivity.show(this);
            finish();
        }
    }
}
