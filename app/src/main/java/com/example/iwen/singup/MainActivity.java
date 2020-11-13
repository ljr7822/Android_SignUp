package com.example.iwen.singup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.widget.PortraitView;
import com.example.iwen.singup.fragment.ContactFragment;
import com.example.iwen.singup.fragment.HomeFragment;
import com.example.iwen.singup.fragment.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    // 标题
    @BindView(R.id.appbar)
    View mLayAppbar;
    // 头像
    @BindView(R.id.im_portrait)
    PortraitView mPortrait;
    // 居中的文字
    @BindView(R.id.txt_title)
    TextView mTitle;
    // 中间部分内容
    @BindView(R.id.lay_container)
    FrameLayout mContainer;
    // 底部导航
    @BindView(R.id.navigation)
    BottomNavigationView mNavigation;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mNavigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick(R.id.im_search)
    void onSearchMenuClick() {

    }

    @OnClick(R.id.btn_action)
    void onActionClick() {

    }

    boolean isFirst = true;

    /**
     * Navigation选中事件
     *
     * @param item 哪个Item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_home) {
            // 改变Title
            mTitle.setText(R.string.title_home);
            HomeFragment homeFragment = new HomeFragment();
            if (isFirst) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.lay_container, homeFragment)
                        .commit();
                isFirst = false;
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lay_container, homeFragment)
                        .commit();

            }
        } else if (item.getItemId() == R.id.action_me) {
            // 改变Title
            mTitle.setText(R.string.action_me);
            MineFragment mineFragment = new MineFragment();

            if (isFirst) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.lay_container, mineFragment)
                        .commit();
                isFirst = false;
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lay_container, mineFragment)
                        .commit();
            }
        }else if (item.getItemId() == R.id.action_contact){
            // 改变Title
            mTitle.setText(R.string.action_contact);
            ContactFragment contactFragment = new ContactFragment();

            if (isFirst){
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.lay_container, contactFragment)
                        .commit();
                isFirst = false;
            }else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.lay_container, contactFragment)
                        .commit();
            }
        }
        // 改变Title
        mTitle.setText(item.getTitle());
        // 变更内容

        return true;
    }
}