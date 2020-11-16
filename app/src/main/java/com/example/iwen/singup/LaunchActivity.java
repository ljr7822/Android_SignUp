package com.example.iwen.singup;

import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.singup.activities.MainActivity;
import com.example.iwen.singup.fragment.assist.PermissionsFragment;

/**
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
            MainActivity.show(this);
            finish();
        }
    }
}
