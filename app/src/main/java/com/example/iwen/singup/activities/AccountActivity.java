package com.example.iwen.singup.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.singup.R;
import com.example.iwen.singup.fragment.account.AccountTrigger;
import com.example.iwen.singup.fragment.account.LoginFragment;
import com.example.iwen.singup.fragment.account.RegisterFragment;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.compat.UiCompat;

import butterknife.BindView;

/**
 * 账户的Activity
 * author : Iwen大大怪
 * create : 2020/11/15 9:27
 */
public class AccountActivity extends BaseActivity implements AccountTrigger {
    private BaseFragment mFragment;
    private BaseFragment mLoginFragment;
    private BaseFragment mRegisterFragment;

    @BindView(R.id.im_bg)
    ImageView mBg;

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
        // 初始化Fragment，默认的为登录
        mFragment = mLoginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.lay_container, mFragment)
                .commit();
        // 初始化背景
        Glide.with(this)
                .load(R.mipmap.bg_src_tianjin)
                .centerCrop()
                .into(new CustomViewTarget<ImageView, Drawable>(mBg) {
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        // 拿到glide的drawable
                        Drawable drawable = resource.getCurrent();
                        // 使用适配包包装
                        drawable = DrawableCompat.wrap(drawable);
                        drawable.setColorFilter(UiCompat.getColor(getResources(), R.color.colorAccent),
                                PorterDuff.Mode.SCREEN); // 设置着色效果
                        // 设置给ImageView
                        this.view.setImageDrawable(drawable);
                    }

                    @Override
                    protected void onResourceCleared(@Nullable Drawable placeholder) {

                    }
                });
    }

    /**
     * 切换具体的fragment
     * 登录、注册相互切换
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
