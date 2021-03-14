package com.example.iwen.singup.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.common.app.PresenterActivity;
import com.example.iwen.common.widget.PortraitView;
import com.example.iwen.factory.model.db.account.UserRspModel;
import com.example.iwen.factory.model.db.location.LocationTaskList;
import com.example.iwen.factory.presenter.account.UserContract;
import com.example.iwen.factory.presenter.account.UserPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.fragment.user.UpdateInfoFragment;

import butterknife.BindView;

/**
 * 用户的activity
 */
public class UserActivity extends PresenterActivity<UserContract.Presenter> implements UserContract.View {
    private BaseFragment mFragment;
    // 头像
    @BindView(R.id.im_portrait)
    PortraitView mPortraitView;
    // 名称
    @BindView(R.id.user_name)
    TextView mUserName;
    // 部门
    @BindView(R.id.user_department)
    TextView mDepartment;

    /**
     * 打卡Activity显示入口
     * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 绑定layout
     */
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_user;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }

    // 收到从Activity传过来的回调，取出其中的值进行图片加载
    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragment.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected UserContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void userCardSuccess(UserRspModel userRspModel) {
    }
}