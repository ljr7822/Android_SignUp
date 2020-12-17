package com.example.iwen.singup.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.singup.R;
import com.example.iwen.singup.fragment.ContactFragment;
import com.example.iwen.singup.fragment.HomeFragment;
import com.example.iwen.singup.fragment.MineFragment;
import com.example.iwen.singup.helper.NavHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.qiujuer.genius.ui.Ui;
import net.qiujuer.genius.ui.widget.FloatActionButton;

import java.util.Objects;

public class MainActivity
        extends BaseActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, NavHelper.OnTabChangedListener<Integer> {

    private View mLayAppbar;
    private TextView mTitle;
    private ImageView mSetting;
    private FrameLayout mContainer;
    private BottomNavigationView mNavigation;
    private FloatActionButton mAction;

    boolean isFirst = true;

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
        // 打卡按钮，跳转到打卡界面
        mAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignActivity.class);
                startActivity(intent);
                // SignActivity.show(getApplicationContext(),null);
            }
        });
        // 设置跳转按钮
        mSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent1);
                //SettingActivity.show(getApplicationContext());
            }
        });
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
            mAction.setImageResource(R.drawable.ic_sign_in);
            rotation = -360;
        } else {
            // 消息
            if (Objects.equals(newTab.extra, R.string.title_content)) {
                mAction.setImageResource(R.drawable.ic_sign_in);
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
}