package com.example.iwen.singup.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.singup.R;
import com.google.android.material.appbar.AppBarLayout;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import net.qiujuer.genius.ui.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {
    // 顶部导航背景
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    // 返回按钮
    @BindView(R.id.im_back)
    ImageView mBack;
    // 退出登录按钮
    @BindView(R.id.btn_logout)
    Button mLogoutButton;
    // 关于我们
    @BindView(R.id.setting_about)
    LinearLayout mSettingAboutLayout;

    /**
     * 打卡Activity显示入口
     * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 给内容部分设置背景图片
        Glide.with(this)
                .load(R.mipmap.top_bg)
                .centerCrop()
                .into(new CustomViewTarget<View, Drawable>(mAppBarLayout) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        this.view.setBackground(resource.getCurrent());
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    /**
     * 返回按钮
     */
    @OnClick(R.id.im_back)
    void onBackClick() {
        finish();
    }

    /**
     * 退登录按钮
     */
    @OnClick(R.id.btn_logout)
    void onLogoutClick() {
        showXPopupLogout(this, "确认", "退出后将断开定位服务，确认退出？");
    }

    /**
     * 关于我们
     */
    @OnClick(R.id.setting_about)
    void onAboutClick() {
        AboutActivity.show(this);
    }

    /**
     * 确认用户是否退出登录
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showXPopupLogout(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "取消",
                        "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                logout();
                                finish();
                            }
                        },
                        new OnCancelListener() {
                            @Override
                            public void onCancel() {
                            }
                        }, false)
                .show();
    }

    /**
     * 退出登录方法
     */
    private void logout() {
        // 清空持久化数据
        SPUtils.remove(this, "isLogin");
        SPUtils.remove(this, "workId");
        SPUtils.remove(this, "password");
        // 返回登录界面
        AccountActivity.show(this);
    }
}