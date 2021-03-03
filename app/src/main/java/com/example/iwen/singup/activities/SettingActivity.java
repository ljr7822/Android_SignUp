package com.example.iwen.singup.activities;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.PushMessage;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.Common;
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
    // 轨迹开关
    @BindView(R.id.btn_switch)
    Switch mSwitch;

    private String UserName;
    private LBSTraceClient mTraceClient;
    private OnTraceListener mTraceListener;
    private int isStatus;



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
        UserName = (String) SPUtils.get(this, "name", "李俊然");
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

        /**
         * 获取用户轨迹
         */
        // 轨迹服务ID  225246
        long serviceId = Common.Constance.serviceId;
        // 设备标识
        String entityName = UserName;
        // 是否需要对象存储服务，默认为：false，关闭对象存储服务。
        boolean isNeedObjectStorage = false;
        // 初始化轨迹服务
        final com.baidu.trace.Trace mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
        // 初始化轨迹服务客户端
        mTraceClient = new LBSTraceClient(getApplicationContext());
        // 定位周期(单位:秒)
        int gatherInterval = 5;
        // 打包回传周期(单位:秒)
        int packInterval = 10;
        // 设置定位和打包周期
        mTraceClient.setInterval(gatherInterval, packInterval);
        // 初始化轨迹服务监听器
        mTraceListener = new OnTraceListener() {
            /**
             * 绑定服务回调接口
             * @param i  状态码
             * @param s 消息
             *                <p>
             *                <pre>0：成功 </pre>
             *                <pre>1：失败</pre>
             */
            @Override
            public void onBindServiceCallback(int i, String s) {
                Toast.makeText(getApplicationContext(), "绑定服务:" + i + ":" + s, Toast.LENGTH_SHORT).show();
            }

            /**
             * 开启服务回调接口
             * @param status 状态码
             * @param message 消息
             *                <p>
             *                <pre>0：成功 </pre>
             *                <pre>10000：请求发送失败</pre>
             *                <pre>10001：服务开启失败</pre>
             *                <pre>10002：参数错误</pre>
             *                <pre>10003：网络连接失败</pre>
             *                <pre>10004：网络未开启</pre>
             *                <pre>10005：服务正在开启</pre>
             *                <pre>10006：服务已开启</pre>
             */
            @Override
            public void onStartTraceCallback(int status, String message) {
                Toast.makeText(getApplicationContext(), "开启服务:" + status + ":" + message, Toast.LENGTH_SHORT).show();
                if (status==0) {
                    waitStartGather();
                }
            }

            /**
             * 停止服务回调接口
             * @param status 状态码
             * @param message 消息
             *                <p>
             *                <pre>0：成功</pre>
             *                <pre>11000：请求发送失败</pre>
             *                <pre>11001：服务停止失败</pre>
             *                <pre>11002：服务未开启</pre>
             *                <pre>11003：服务正在停止</pre>
             */
            @Override
            public void onStopTraceCallback(int status, String message) {
                Toast.makeText(getApplicationContext(), "停止服务:" + status + ":" + message, Toast.LENGTH_SHORT).show();
            }

            /**
             * 开启采集回调接口
             * @param status 状态码
             * @param message 消息
             *                <p>
             *                <pre>0：成功</pre>
             *                <pre>12000：请求发送失败</pre>
             *                <pre>12001：采集开启失败</pre>
             *                <pre>12002：服务未开启</pre>
             */
            @Override
            public void onStartGatherCallback(int status, String message) {
                Toast.makeText(getApplicationContext(), "开启采集:" + status + ":" + message, Toast.LENGTH_SHORT).show();
            }

            /**
             * 停止采集回调接口
             * @param status 状态码
             * @param message 消息
             *                <p>
             *                <pre>0：成功</pre>
             *                <pre>13000：请求发送失败</pre>
             *                <pre>13001：采集停止失败</pre>
             *                <pre>13002：服务未开启</pre>
             */
            @Override
            public void onStopGatherCallback(int status, String message) {
                Toast.makeText(getApplicationContext(), "停止采集:" + status + ":" + message, Toast.LENGTH_SHORT).show();
            }

            /**
             * 推送消息回调接口
             *
             * @param messageNo 状态码
             * @param message 消息
             *                  <p>
             *                  <pre>0x01：配置下发</pre>
             *                  <pre>0x02：语音消息</pre>
             *                  <pre>0x03：服务端围栏报警消息</pre>
             *                  <pre>0x04：本地围栏报警消息</pre>
             *                  <pre>0x05~0x40：系统预留</pre>
             *                  <pre>0x41~0xFF：开发者自定义</pre>
             */
            @Override
            public void onPushCallback(byte messageNo, PushMessage message) {
            }

            @Override
            public void onInitBOSCallback(int i, String s) {
                Toast.makeText(getApplicationContext(), i + ":" + s, Toast.LENGTH_SHORT).show();
            }
        };

        // 开启轨迹跟踪服务
        if ((boolean) SPUtils.get(getApplicationContext(), "isTrace", false)) {
            mSwitch.setChecked(true);
            mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //控制开关字体颜色
                    if (isChecked) {
                        //mSwitch.setTextOn("on");
                        //mSwitch.setSwitchTextAppearance(SettingActivity.this,R.style.s_true);
                        // 开启服务
                        mTraceClient.startTrace(mTrace, mTraceListener);
                        SPUtils.put(getApplicationContext(), "isTrace", true);
                    } else {
                        //mSwitch.setTextOff("off");
                        //mSwitch.setSwitchTextAppearance(SettingActivity.this,R.style.s_false);
                        // 弹窗提示
                        //Toasty.error(getApplicationContext(), "轨迹上传服务关闭", Toasty.LENGTH_SHORT).show();
                        // 停止服务
                        mTraceClient.stopTrace(mTrace, mTraceListener);
                        // 结束采集
                        mTraceClient.stopGather(mTraceListener);
                        SPUtils.remove(getApplicationContext(), "isTrace");
                        SPUtils.put(getApplicationContext(), "isTrace", false);
                    }
                }
            });
        } else {
            mSwitch.setChecked(false);
            mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //控制开关字体颜色
                    if (isChecked) {
                        //mSwitch.setTextOn("on");
                        //mSwitch.setSwitchTextAppearance(SettingActivity.this,R.style.s_true);
                        // 弹窗提示
                        //Toasty.success(getApplicationContext(), "轨迹上传服务开启", Toasty.LENGTH_SHORT).show();
                        // 开启服务
                        mTraceClient.startTrace(mTrace, mTraceListener);
                        SPUtils.put(getApplicationContext(), "isTrace", true);
                    } else {
                        //mSwitch.setTextOff("off");
                        //mSwitch.setSwitchTextAppearance(SettingActivity.this,R.style.s_false);
                        // 弹窗提示
                        //Toasty.error(getApplicationContext(), "轨迹上传服务关闭", Toasty.LENGTH_SHORT).show();
                        // 结束采集
                        mTraceClient.stopGather(mTraceListener);
                        // 停止服务
                        mTraceClient.stopTrace(mTrace, mTraceListener);
                        SPUtils.remove(getApplicationContext(), "isTrace");
                        SPUtils.put(getApplicationContext(), "isTrace", false);
                    }
                }
            });
        }
    }

    private void waitStartGather() {
        mTraceClient.startGather(mTraceListener);
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