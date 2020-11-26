package com.example.iwen.singup.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwen.common.app.BaseFragment;

import com.example.iwen.common.widget.MessageItemTouchHelperCallback;
import com.example.iwen.common.widget.SpacesItemDecoration;
import com.example.iwen.common.widget.banner.DataBean;
import com.example.iwen.common.widget.banner.ImageAdapter;
import com.example.iwen.common.widget.cementitem.CementRecyclerViewAdapter;
import com.example.iwen.common.widget.cementitem.Cements;
import com.example.iwen.singup.R;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.youth.banner.Banner;

import com.youth.banner.indicator.CircleIndicator;

import com.youth.banner.transformer.RotateYTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private List<Cements> mCementsList = new ArrayList<>();
    private CementRecyclerViewAdapter mCementRecyclerViewAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private ImageAdapter mImageAdapter;
    private Banner mBanner;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView = rootView.findViewById(R.id.home_recycler);
        mBanner = rootView.findViewById(R.id.banner);
        mCementRecyclerViewAdapter = new CementRecyclerViewAdapter(mCementsList, getActivity());
        recyclerView.setLayoutManager(layout);
        mCementsList = generateDummyList(10);
        recyclerView.addItemDecoration(new SpacesItemDecoration(0));
        ItemTouchHelper.Callback callback = new MessageItemTouchHelperCallback(mCementRecyclerViewAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        recyclerView.setAdapter(mCementRecyclerViewAdapter);
        showBanner();
        return rootView;
    }

    /**
     * 顶部广告轮播图
     */
    public void showBanner() {
        // 自定义的图片适配器，也可以使用默认的BannerImageAdapter
        mImageAdapter = new ImageAdapter(DataBean.getTestData());
        mBanner.setAdapter(mImageAdapter)
                .addBannerLifecycleObserver(this)// 添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()))// 设置指示器
                .setPageTransformer(new RotateYTransformer()) // 切换效果
                .setOnBannerListener((data, position) -> { // 点击事件
                    showXPopupSelect(getContext(), "公告", "打了么App，欢迎您！\n " + ((DataBean) data).title.toString());
                });
    }

    // 模拟数据
    private List<Cements> generateDummyList(int size) {
        mCementsList = new ArrayList<Cements>();
        for (int i = 0; i < size; i++) {
            int drawable = R.drawable.white;
            Cements cements = new Cements("公告",
                    "打了么隆重推出定位服务", "2020.11.23", drawable);
            mCementsList.add(cements);
        }
        return mCementsList;
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