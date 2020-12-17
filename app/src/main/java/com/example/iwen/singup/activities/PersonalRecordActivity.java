package com.example.iwen.singup.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.PresenterActivity;
import com.example.iwen.common.widget.recycler.RecyclerAdapter;
import com.example.iwen.factory.model.db.account.PersonalRecordRspModel;
import com.example.iwen.factory.model.db.location.LocationTaskList;
import com.example.iwen.factory.presenter.account.PersonalRecordContract;
import com.example.iwen.factory.presenter.account.PersonalRecordPresenter;
import com.example.iwen.factory.presenter.sign.SignContract;
import com.example.iwen.singup.R;
import com.example.iwen.singup.fragment.ContactFragment;
import com.google.android.material.appbar.AppBarLayout;

import java.security.PermissionCollection;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalRecordActivity
        extends PresenterActivity<PersonalRecordContract.Presenter>
        implements PersonalRecordContract.View {

    private RecyclerAdapter<PersonalRecordRspModel> mAdapter;

    // 顶部导航背景
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    // 返回按钮
    @BindView(R.id.im_personal_record_back)
    ImageView mBack;
    // recycler_view
    @BindView(R.id.personal_record_recycler_view)
    RecyclerView mRecycler;

    /**
     * 签到记录Activity显示入口
     * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        Intent intent = new Intent(context, PersonalRecordActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 绑定视图
     */
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_personal_record;
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
    @OnClick(R.id.im_personal_record_back)
    void onBackClick() {
        finish();
    }

    @Override
    protected PersonalRecordContract.Presenter initPresenter() {
        return new PersonalRecordPresenter(this);
    }

    @Override
    public void getPersonalRecordSuccess(List<PersonalRecordRspModel> personalRecordRspModelList) {

    }
}