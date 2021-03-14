package com.example.iwen.singup;

import android.accounts.Account;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;

import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.utils.DeviceIdUtil;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.singup.activities.AccountActivity;
import com.example.iwen.singup.activities.MainActivity;
import com.example.iwen.singup.fragment.assist.PermissionsFragment;

import net.qiujuer.genius.ui.compat.UiCompat;

/**
 * 启动页面
 *
 * @author : Iwen大大怪
 * create : 2020/11/16 9:59
 */
public class LaunchActivity extends BaseActivity {
    // Drawable
    private ColorDrawable mBgDrawable;
    // 登录标志：判断是否存在登录
    private boolean isLogin = false;
    // 是否已经得到PushId
    private boolean mAlreadyGotPushReceiverId = false;
    // 完善信息标志：判断是否需要完善
    private boolean isInfo;

    /**
     * 绑定视图
     */
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 拿到根布局
        View root = findViewById(R.id.activity_launch);
        // 获取颜色
        int color = UiCompat.getColor(getResources(), R.color.white);
        // 创建一个Drawable
        ColorDrawable drawable = new ColorDrawable(color);
        // 设置给背景
        root.setBackground(drawable);
        mBgDrawable = drawable;
    }

    @Override
    protected void initData() {
        super.initData();
        // 手机一启动就立马获取DeviceId并保存到手机中
        SPUtils.put(this, "DeviceId", DeviceIdUtil.getDeviceId(this));
        isInfo = (boolean) SPUtils.get(this,"isInfo",false);
        if (isInfo){
            SPUtils.put(this, "isInfo", true);
        }
        // 开始动画
        startAnim(0.5f, this::waitPushReceiverId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 判断是否已经得到推送Id，如果已经得到则进行跳转操作，
        // 在操作中检测权限状态
        if (mAlreadyGotPushReceiverId) {
            realSkip();
        }
    }

    /**
     * 等待个推框架对我们的pushId设置好值
     */
    private void waitPushReceiverId() {
        if (isLogin) {
            // 已经登录，判断是否绑定
            // 如果没有绑定则等待广播接收器进行绑定
            if (mAlreadyGotPushReceiverId) {
                // 已经绑定
                skip();
                return;
            }
        } else {
            waitPushReceiverIdDone();
            return;
        }

        // 循环等待
        getWindow().getDecorView().postDelayed(this::waitPushReceiverId, 500);
    }

    /**
     * 跳转之前需要将剩下的50%进行完成
     */
    private void skip() {
        startAnim(1f, this::realSkip);
    }

    /**
     * 在跳转之前需要把剩下的50%进行完成
     */
    private void waitPushReceiverIdDone() {
        // 标志已经得到PushId
        mAlreadyGotPushReceiverId = true;
        startAnim(1f, this::realSkip);
    }

    private void realSkip() {
        // 权限检查，跳转
        if (PermissionsFragment.haveAll(this, getSupportFragmentManager())) {
            // 判断是否已经登录
            isLogin = (boolean) SPUtils.get(this, "isLogin", false);
            if (isLogin) {
                // 已经登录
                MainActivity.show(this);
                finish();
            } else {
                // 进入注册登录界面
                AccountActivity.show(this);
                //MainActivity.show(this);
                finish();
            }
        }
    }

    /**
     * 给背景设置一个动画
     *
     * @param endProgress 动画的结束进度
     * @param endCallback 动画结束时触发
     */
    private void startAnim(float endProgress, final Runnable endCallback) {
        // 获取最终的颜色
        int finalColor = UiCompat.getColor(getResources(), R.color.theme);
        // 运算当前进度的颜色
        ArgbEvaluator evaluator = new ArgbEvaluator();
        int endColor = (int) evaluator.evaluate(endProgress, mBgDrawable.getColor(), finalColor);
        // 构建一个属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofObject(this, property, evaluator, endColor);
        valueAnimator.setDuration(3500); // 设置时间
        valueAnimator.setIntValues(mBgDrawable.getColor(), endColor);// 开始结束值设置
        valueAnimator.addListener(new AnimatorListenerAdapter() { // 添加状态器
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // 结束时触发
                endCallback.run();
            }
        });
        valueAnimator.start();
    }

    private final Property<LaunchActivity, Object> property = new Property<LaunchActivity, Object>(Object.class, "color") {
        @Override
        public Object get(LaunchActivity launchActivity) {
            return launchActivity.mBgDrawable.getColor();
        }

        @Override
        public void set(LaunchActivity object, Object value) {
            object.mBgDrawable.setColor((Integer) value);
        }
    };
}
