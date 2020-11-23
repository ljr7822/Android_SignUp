package com.example.iwen.singup.fragment;

import android.content.Context;

import com.example.iwen.common.app.BaseFragment;

import com.example.iwen.common.widget.banner.DataBean;
import com.example.iwen.common.widget.banner.ImageAdapter;
import com.example.iwen.singup.R;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.youth.banner.Banner;

import com.youth.banner.indicator.CircleIndicator;

import com.youth.banner.transformer.RotateYTransformer;

import butterknife.BindView;


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
                    showXPopupSelect(getContext(), "公告", "打了么App，欢迎您！\n " + ((DataBean) data).title.toString());
                });
    }

    /**
     * 显示确认和取消对话框
     * 公告栏
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public static void showXPopupSelect(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "我知道了",
                        "查看详情",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                // TODO 跳转公告
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