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
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.factory.model.db.User;
import com.example.iwen.singup.R;
import com.example.iwen.singup.fragment.ContactFragment;
import com.example.iwen.singup.fragment.HomeFragment;
import com.example.iwen.singup.fragment.MineFragment;
import com.example.iwen.singup.helper.NavHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.v3.MessageDialog;
//import com.lxj.xpopup.XPopup;
//import com.lxj.xpopup.interfaces.OnCancelListener;
//import com.lxj.xpopup.interfaces.OnConfirmListener;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.FloatActionButton;

import java.util.Objects;

public class MainActivity
        extends BaseActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener,
        NavHelper.OnTabChangedListener<Integer> {

    private View mLayAppbar;
    private TextView mTitle;
    private ImageView mSetting;
    private FrameLayout mContainer;
    private BottomNavigationView mNavigation;
    private FloatActionButton mAction;
    private BaseFragment mFragment;
    private Notification notification = null;
    private NotificationManager notificationManager = null;

    private NavHelper<Integer> mNavHelper;

    /**
     * 绑定视图
     */
    private void initView() {
        mLayAppbar = findViewById(R.id.appbar);
        mTitle = findViewById(R.id.txt_title);
        mSetting = findViewById(R.id.im_setting);
        mContainer = findViewById(R.id.lay_container);
        mNavigation = findViewById(R.id.navigation);
        mAction = findViewById(R.id.btn_action);
    }

    /**
     * mainActivity显示入口
     * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected boolean initArgs(Bundle bundle) {
        return super.initArgs(bundle);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initView();
        // 初始化底部辅助工具类,添加底部导航
        mNavHelper = new NavHelper<Integer>(this, R.id.lay_container, getSupportFragmentManager(), this);
        mNavHelper.add(R.id.action_home, new NavHelper.Tab<>(HomeFragment.class, R.string.title_home))
                .add(R.id.action_me, new NavHelper.Tab<>(MineFragment.class, R.string.title_mine_top))
                .add(R.id.action_contact, new NavHelper.Tab<>(ContactFragment.class, R.string.title_content));
        // 添加对底部导航按钮的监听
        mNavigation.setOnNavigationItemSelectedListener(this);
        // 给顶部appbar设置背景图片
        Glide.with(this)
                .load(R.mipmap.top_bg)
                .centerCrop()
                .into(new CustomViewTarget<View, Drawable>(mLayAppbar) {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNotification();
        // 打卡按钮，跳转到打卡界面
        mAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到指示界面
                // Intent intent = new Intent(MainActivity.this, SignActivity.class);
                // startActivity(intent);
                showMessageDialogSteps(MainActivity.this,"使用步骤",
                        "1.点击消息列表查看签到信息\n" +
                                "2.进入某个签到信息页，进行签到\n" +
                                "3.初次登陆请完善个人信息");
                // SignActivity.show(getApplicationContext(),null);
            }
        });
        // 设置跳转按钮
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent1);
                //SettingActivity.show(getApplicationContext());
            }
        });

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 操作流程弹窗
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showMessageDialogSteps(Context context, String title, String content) {
        MessageDialog.build((AppCompatActivity) context)
                .setTitle(title)
                .setMessage(content)
                .setOkButton("知道了", new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View view) {
                        return false;
                    }
                })
                .show();

//        new XPopup.Builder(context)
//                .hasBlurBg(true)
//                .asConfirm(title, content,
//                        "不了",
//                        "前往拍照",
//                        new OnConfirmListener() {
//                            @Override
//                            public void onConfirm() {
//                                // 跳转拍照的fragment页面进行拍照
//                                Intent intent = new Intent(SignActivity.this, TakePictureActivity.class);
//                                intent.putExtra("name", name);
//                                intent.putExtra("workId", workId);
//                                intent.putExtra("department", department);
//                                startActivity(intent);
//                            }
//                        },
//                        new OnCancelListener() {
//                            @Override
//                            public void onCancel() {
////                                ToastUtils.Builder builder = new ToastUtils.Builder(context);
////                                builder.setLayout(R.layout.view_layout_toast_done)
////                                        .setGravity(Gravity.CENTER)
////                                        .build()
////                                        .show();
//                            }
//                        }, false)
//                .show();
    }


    @Override
    protected void initData() {
        super.initData();
        // 第一次进去触发选择
        // 从底部导航接管Menu，进行手动触发第一次点击
        Menu menu = mNavigation.getMenu();
        // 触发首次选中home
        menu.performIdentifierAction(R.id.action_home, 0);
    }

    /**
     * Navigation选中事件
     *
     * @param item 哪个Item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // 转接事件工具流到攻击类
        return mNavHelper.performClickMenu(item.getItemId());
    }

    /**
     * navHelper处理后回调的方法
     *
     * @param newTab 新Tab
     * @param oldTab 旧Tab
     */
    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {
        // 从额外字段中取出我的title资源id
        mTitle.setText(newTab.extra);

        // 对浮动按钮进行隐藏、显示
        float transY = 0;
        float rotation = 0;
        if (Objects.equals(newTab.extra, R.string.title_home)) {

            // transY = Ui.dipToPx(getResources(),86);
            mAction.setImageResource(R.drawable.ic_tip);
            rotation = -360;
        } else {
            // 消息
            if (Objects.equals(newTab.extra, R.string.title_content)) {
                mAction.setImageResource(R.drawable.ic_tip);
                rotation = 360;
            } else {
                // 我的界面时隐藏
//                mAction.setImageResource(R.drawable.ic_sign_in);
//                rotation = 360;
                transY = Ui.dipToPx(getResources(), 86);
            }
        }
        // 开始动画
        mAction.animate()
                .rotation(rotation)
                .translationY(transY)
                .setInterpolator(new AnticipateOvershootInterpolator(1))
                .setDuration(480)
                .start();
    }

    @TargetApi(16)
    private void initNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent notificationIntent = new Intent(this, SettingActivity.class);

        Bitmap icon = BitmapFactory.decodeResource(this.getResources(),
                R.mipmap.icon_sign_up);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 设置PendingIntent
        builder.setContentIntent(PendingIntent.getActivity(this, 0, notificationIntent, 0))
                .setLargeIcon(icon)  // 设置下拉列表中的图标(大图标)
                .setContentTitle("打了么") // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.icon_sign_up) // 设置状态栏内的小图标
                .setContentText("服务正在运行...") // 设置上下文内容
                .setWhen(System.currentTimeMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && null != notificationManager) {
            NotificationChannel notificationChannel =
                    new NotificationChannel("trace", "trace_channel",
                            NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);

            builder.setChannelId("trace"); // Android O版本之后需要设置该通知的channelId
        }

        notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; // 设置为默认的声音
    }
}