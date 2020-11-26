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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.singup.BuildConfig;
import com.example.iwen.singup.R;

import butterknife.BindView;
import butterknife.OnClick;
import me.drakeet.multitype.Items;
import me.drakeet.support.about.AbsAboutActivity;
import me.drakeet.support.about.Card;
import me.drakeet.support.about.Category;
import me.drakeet.support.about.Contributor;

/**
 * 关于我们的Activity
 */
public class AboutActivity extends BaseActivity {
    // 标题
    @BindView(R.id.setting_about_appbar)
    View mLayAppbar;
    // 返回按钮
    @BindView(R.id.im_about_back)
    ImageView mBack;

    /**
     * 关于我们Activity显示入口
     * @param context 上下文
     */
    public static void show(Context context){
        context.startActivity(new Intent(context,AboutActivity.class));
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_about;
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
    }

    // 返回按钮
    @OnClick(R.id.im_about_back)
    void onBackClick(){
        finish();
    }

//    @Override
//    protected void onCreateHeader(@NonNull ImageView icon, @NonNull TextView slogan, @NonNull TextView version) {
//        icon.setImageResource(R.mipmap.logo);
//        slogan.setText("生命不息，奋斗不止");
//        version.setText("v" + BuildConfig.VERSION_NAME);
//    }
//
//    @Override
//    protected void onItemsCreated(@NonNull Items items) {
//        items.add(new Category("介绍"));
//        items.add(new Card(getString(R.string.about_app)));
//
////        items.add(new Category("功能特性"));
////        items.add(new Card(getString(R.string.about_function)));
//
//        items.add(new Category("意见反馈"));
//        items.add(new Card(getString(R.string.about_feedback)));
//
//        items.add(new Category("开发者"));
//        items.add(new Contributor(R.mipmap.designer5, "18信管1班 李俊然", "Developer & designer", "https://github.com/ljr7822"));
//        items.add(new Category("开发者"));
//        items.add(new Contributor(R.mipmap.designer4, "18信管1班 艾常鹏", "Developer & designer", "https://github.com/ljr7822"));
//        items.add(new Category("开发者"));
//        items.add(new Contributor(R.mipmap.designer3, "18信管1班 张曼洋", "Developer & designer", "https://github.com/ljr7822"));
//        items.add(new Category("开发者"));
//        items.add(new Contributor(R.mipmap.designer2, "18计科4班 陈敏", "Developer & designer", "https://github.com/ljr7822"));
//        items.add(new Category("开发者"));
//        items.add(new Contributor(R.mipmap.designer1, "20信管1班 雷鑫", "Developer & designer", "https://github.com/ljr7822"));
//
//        items.add(new Category("项目地址"));
//        items.add(new Contributor(R.mipmap.github, "Github", "打了么", "https://github.com/ljr7822"));
//    }
}