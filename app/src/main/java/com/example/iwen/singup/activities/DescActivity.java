package com.example.iwen.singup.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.factory.model.db.location.LocationTaskList;
import com.example.iwen.singup.R;
import com.google.android.material.appbar.AppBarLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 签到详情的Activity
 */
public class DescActivity extends BaseActivity {
    // 顶部导航背景
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    // 返回按钮
    @BindView(R.id.im_back)
    ImageView mBack;
    // 姓名
    @BindView(R.id.tv_name)
    TextView mName;
    // 工号
    @BindView(R.id.tv_workIb)
    TextView mWorkId;
    // 部门
    @BindView(R.id.tv_department)
    TextView mDepartment;
    // 签到时间
    @BindView(R.id.tv_date)
    TextView mDate;
    // 签到位置
    @BindView(R.id.tv_adder)
    TextView mAdder;
    // 附加消息名称
    @BindView(R.id.tv_title)
    TextView mTitle;
    // 附加消息
    @BindView(R.id.tv_t)
    TextView mT;

    private LocationTaskList mLocationTaskList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_desc;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        Glide.with(this)
                .load(R.mipmap.top_bg)
                .centerCrop()
                .into(new CustomViewTarget<View, Drawable>(mAppBarLayout){
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

        Intent intent = getIntent();
        mName.setText(SPUtils.get(this,"name","iwen").toString());
        mWorkId.setText(SPUtils.get(this,"workId","10010001").toString());
        mDepartment.setText(intent.getStringExtra("departmentId"));
        mDate.setText(intent.getStringExtra("date"));
        mAdder.setText(intent.getStringExtra("adder"));
        mTitle.setText(intent.getStringExtra("title"));
        mT.setText(intent.getStringExtra("info"));
    }

    /**
     * 返回按钮
     */
    @OnClick(R.id.im_back)
    void onBackClick() {
        finish();
    }
}