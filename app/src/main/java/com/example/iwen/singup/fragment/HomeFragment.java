package com.example.iwen.singup.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.iwen.common.app.Application;
import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.common.widget.GalleryView;
import com.example.iwen.common.widget.banner.DataBean;
import com.example.iwen.common.widget.banner.ImageAdapter;
import com.example.iwen.singup.R;
import com.google.android.material.snackbar.Snackbar;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.config.BannerConfig;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.RotateDownPageTransformer;
import com.youth.banner.transformer.RotateUpPageTransformer;
import com.youth.banner.transformer.RotateYTransformer;
import com.youth.banner.transformer.ScaleInTransformer;
import com.youth.banner.transformer.ZoomOutPageTransformer;
import com.youth.banner.util.LogUtils;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment {
    // 轮播图控件
    @BindView(R.id.banner)
    Banner banner;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        super.initData();

        // 自定义的图片适配器，也可以使用默认的BannerImageAdapter
        ImageAdapter adapter = new ImageAdapter(DataBean.getTestData());

        banner.setAdapter(adapter)
                .addBannerLifecycleObserver(this)// 添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()))// 设置指示器
                .setPageTransformer(new RotateYTransformer()) // 切换效果
                .setOnBannerListener((data, position) -> { // 点击事件
                    Application.showToast(((DataBean) data).title.toString());
                });
    }
}