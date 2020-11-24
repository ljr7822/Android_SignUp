package com.example.iwen.singup.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
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

    /**
     * 打卡Activity显示入口
     * @param context 上下文
     */
    public static void show(Context context){
        context.startActivity(new Intent(context,SettingActivity.class));
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
    void onBackClick(){
        finish();
    }

    /**
     * 退登录按钮
     */
    @OnClick(R.id.btn_logout)
    void onLogoutClick(){
        showXPopupLogout(this,"确认","退出后将断开定位服务，确认退出？");
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
                                // TODO 退出登录操作
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
}