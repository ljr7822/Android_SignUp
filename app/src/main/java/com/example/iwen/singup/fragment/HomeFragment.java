package com.example.iwen.singup.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.common.widget.EmptyView;
import com.example.iwen.common.widget.banner.DataBean;
import com.example.iwen.common.widget.banner.ImageAdapter;
import com.example.iwen.common.widget.recycler.RecyclerAdapter;
import com.example.iwen.factory.model.db.notice.NoticeRspModel;
import com.example.iwen.factory.presenter.notice.NoticeContract;
import com.example.iwen.factory.presenter.notice.NoticePresenter;
import com.example.iwen.singup.R;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.RotateYTransformer;

import java.util.List;

import butterknife.BindView;
import me.drakeet.materialdialog.MaterialDialog;

public class HomeFragment extends PresenterFragment<NoticeContract.Presenter>
        implements NoticeContract.View {
    private ImageAdapter mImageAdapter;
    // 空布局
    @BindView(R.id.empty)
    EmptyView mEmptyView;
    // recycler布局
    @BindView(R.id.home_recycler)
    RecyclerView mRecycler;
    // 轮播图
    @BindView(R.id.banner)
    Banner mBanner;

    private RecyclerAdapter<NoticeRspModel> mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * 绑定布局
     */
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    /**
     * 初始化控件
     *
     * @param view 根视图
     */
    @Override
    protected void initWidget(View view) {
        super.initWidget(view);
        // 轮播图
        mImageAdapter = new ImageAdapter(DataBean.getTestData());
        mBanner.setAdapter(mImageAdapter)
                .addBannerLifecycleObserver(this)// 添加生命周期观察者
                .setIndicator(new CircleIndicator(getContext()))// 设置指示器
                .setPageTransformer(new RotateYTransformer()) // 切换效果
                .setOnBannerListener((data, position) -> { // 点击事件
                    // 显示广告详情并且提供跳转
                    //showXPopupAD(getContext(), ((DataBean) data).title.toString(), ((DataBean) data).content.toString(),((DataBean) data).url.toString());
                    final MaterialDialog skipDialog = new MaterialDialog(getContext());
                    skipDialog.setTitle(((DataBean) data).title.toString())
                            .setMessage(((DataBean) data).content.toString())
                            .setPositiveButton("去看看", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    skipWY(((DataBean) data).url.toString());
                                }
                            })
                            .setNegativeButton("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    skipDialog.dismiss();
                                }
                            });
                    skipDialog.show();
                });
        // 初始化适配器
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        // 设置Adapter
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<NoticeRspModel>() {
            @Override
            protected int getItemViewType(int position, NoticeRspModel noticeRspModel) {
                // 返回cell的布局id
                return R.layout.item_home;
            }

            @Override
            protected ViewHolder<NoticeRspModel> onCreateViewHolder(View root, int viewType) {
                return new HomeFragment.ViewHolder(root);
            }
        });
        mEmptyView.bind(mRecycler);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //showBanner();
        // TODO 公告
        mPresenter.getNotice("10010002");
    }

    /**
     * 初始化Presenter
     */
    @Override
    protected NoticeContract.Presenter initPresenter() {
        return new NoticePresenter(this);
    }

    /**
     * 获取成功，刷新列表数据
     *
     * @param noticeRspModel List<NoticeRspModel>
     */
    @Override
    public void getNoticeSuccess(List<NoticeRspModel> noticeRspModel) {
        mAdapter.replace(noticeRspModel);
    }

    /**
     * 每一个cell的布局操作
     */
    static class ViewHolder extends RecyclerAdapter.ViewHolder<NoticeRspModel> {
        // 标题
        @BindView(R.id.cement_title)
        TextView mNoticeTitle;
        // 时间
        @BindView(R.id.cement_time)
        TextView mNoticeTime;
        // 详情
        @BindView(R.id.cement_desc)
        TextView mNoticeDesc;
        // 部门
        @BindView(R.id.tv_department)
        TextView mDepartment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(NoticeRspModel noticeRspModel) {
            mNoticeTitle.setText(noticeRspModel.getTitle());
            mNoticeTime.setText(noticeRspModel.getDate());
            mNoticeDesc.setText(noticeRspModel.getContent());
            mDepartment.setText(noticeRspModel.getDepartmentName());
        }
    }

    /**
     * 广告弹窗跳转
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showXPopupAD(Context context, String title, String content, String url) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                        "取消",
                        "去看看",
                        new OnConfirmListener() {
                            @Override
                            public void onConfirm() {
                                skipWY(url);
                            }
                        }, new OnCancelListener() {
                            @Override
                            public void onCancel() {
                            }
                        }, false)
                .show();
    }

    /**
     * 跳转到外部网页
     *
     * @param url 地址
     */
    public void skipWY(String url) {
        // 方式一：代码实现跳转
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);//此处填链接
        intent.setData(content_url);
        startActivity(intent);
    }
    /**
     * 顶部广告轮播图
     */
//    public void showBanner() {
//        // 自定义的图片适配器，也可以使用默认的BannerImageAdapter
//        mImageAdapter = new ImageAdapter(DataBean.getTestData());
//        mBanner.setAdapter(mImageAdapter)
//                .addBannerLifecycleObserver(this)// 添加生命周期观察者
//                .setIndicator(new CircleIndicator(getContext()))// 设置指示器
//                .setPageTransformer(new RotateYTransformer()) // 切换效果
//                .setOnBannerListener((data, position) -> { // 点击事件
//                    //showXPopupSelect(getContext(), "公告", "打了么App，欢迎您！\n " + ((DataBean) data).title.toString());
//                });
//    }

//    // 模拟数据
//    private List<Cements> generateDummyList(int size) {
//        mCementsList = new ArrayList<Cements>();
//        for (int i = 0; i < size; i++) {
//            Cements cements = new Cements("公告",
//                    "打了么隆重推出定位服务", "2020.11.23");
//            mCementsList.add(cements);
//        }
//        return mCementsList;
//    }

    /**
     * 显示确认和取消对话框
     * 公告栏
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
//    public static void showXPopupSelect(Context context, String title, String content) {
//        new XPopup.Builder(context)
//                .hasBlurBg(true)
//                .asConfirm(title, content,
//                        "我知道了",
//                        "查看详情",
//                        new OnConfirmListener() {
//                            @Override
//                            public void onConfirm() {
//                                // TODO 跳转公告
//                            }
//                        },
//                        new OnCancelListener() {
//                            @Override
//                            public void onCancel() {
//                            }
//                        }, false)
//                .show();
//    }

    /*
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
     */
}