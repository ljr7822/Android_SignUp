package com.example.iwen.singup.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.singup.R;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;

import butterknife.BindView;
import butterknife.OnClick;

public class TakePictureActivity extends BaseActivity {
    // 标题
    @BindView(R.id.appbar)
    View mLayAppbar;
    // 照片预览
    @BindView(R.id.im_take_picture)
    ImageView mTakeView;
    // 拍照按钮
    @BindView(R.id.btn_take)
    Button mTakeButton;
    // 提交按钮
    @BindView(R.id.btn_take_submit)
    Button mTakeSubmitButton;
    // 返回按钮
    @BindView(R.id.im_sign_back)
    ImageView mSignBack;
    // 姓名
    @BindView(R.id.tv_name)
    TextView mName;
    // 工号
    @BindView(R.id.tv_workIb)
    TextView mWorkId;
    // 部门
    @BindView(R.id.tv_department)
    TextView mDepartment;
    // 拍照的用例
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_take_picture;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        // 给内容部分设置背景图片
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
        Intent intent = getIntent();
        mName.setText(intent.getStringExtra("name"));
        mWorkId.setText(intent.getStringExtra("workId"));
        mDepartment.setText(intent.getStringExtra("department"));
    }

    /**
     * 前往拍照
     */
    @OnClick(R.id.btn_take)
    void onGotoTake(){
        dispatchTakePictureIntent();
    }

    /**
     * 返回按钮
     */
    @OnClick(R.id.im_sign_back)
    void onSignBackClick() {
        showXPopupRightBack(this, "确认", "是否退出照片采集页面？退出后将无法完成签到！");
    }

    /**
     * 打开相机拍照
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * 获取拍照的获取缩略图
     *
     * @param requestCode int
     * @param resultCode int
     * @param data Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mTakeView.setImageBitmap(imageBitmap);
        }
    }

    /**
     * 确认用户是否退出拍照
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showXPopupRightBack(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "取消",
                        "确定",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
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