package com.example.iwen.singup.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.common.utils.MDSettingUtils;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.common.widget.EmptyView;
import com.example.iwen.common.widget.banner.DataBean;
import com.example.iwen.common.widget.banner.ImageAdapter;
import com.example.iwen.common.widget.recycler.RecyclerAdapter;
import com.example.iwen.factory.model.db.notice.NoticeRspModel;
import com.example.iwen.factory.presenter.notice.NoticeContract;
import com.example.iwen.factory.presenter.notice.NoticePresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.activities.MainActivity;
import com.example.iwen.singup.activities.UpdateInfoActivity;
import com.example.iwen.singup.fragment.user.UpdateInfoFragment;
import com.kongzue.dialog.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialog.util.BaseDialog;
import com.kongzue.dialog.util.DialogSettings;
import com.kongzue.dialog.v3.CustomDialog;
import com.kongzue.dialog.v3.MessageDialog;
import com.kongzue.dialog.v3.Notification;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.transformer.AlphaPageTransformer;

import java.util.List;

import butterknife.BindView;
import me.drakeet.materialdialog.MaterialDialog;
import com.kongzue.dialog.util.DialogSettings;

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

    // 是否完善信息标志，true为未完善，false为完善,默认为true
    private boolean isInfo;
    private BaseFragment mFragment;

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

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        isInfo = (boolean) SPUtils.get(getContext(),"isInfo",true);
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
                .setPageTransformer(new AlphaPageTransformer()) // 切换效果
                .setOnBannerListener((data, position) -> { // 点击事件
                    // 显示广告详情并且提供跳转
                    showMessageDialogAD(getContext(),
                            ((DataBean) data).title.toString(),
                            ((DataBean) data).content.toString(),
                            ((DataBean) data).url.toString());
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
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<NoticeRspModel>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, NoticeRspModel noticeRspModel) {
                super.onItemClick(holder, noticeRspModel);
                Toast.makeText(getContext(),"点击了公告",Toast.LENGTH_SHORT).show();
            }
        });
        mEmptyView.bind(mRecycler);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化弹窗配置参数
        MDSettingUtils.initDialogSetting();
        // showBanner();
        // 获取公告
        mPresenter.getNotice("");
        // TODO 提示用户完善信息
        userIsInfo(isInfo);
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
        @BindView(R.id.tv_department_name)
        TextView mDepartmentName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(NoticeRspModel noticeRspModel) {
            mNoticeTitle.setText(noticeRspModel.getType());
            mNoticeTime.setText(noticeRspModel.getDate());
            mNoticeDesc.setText(noticeRspModel.getNoticeContents());
            mDepartmentName.setText(noticeRspModel.getDepartmentName());
        }
    }

    /**
     * 广告弹窗跳转
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showMessageDialogAD(Context context, String title, String content, String url) {
        MessageDialog.build((AppCompatActivity) context)
                .setTitle(title)
                .setMessage(content)
                .setOkButton("好，去看看", new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View view) {
                        // 立即跳转
                        skipWY(url);
                        return false;
                    }
                })
                .setCancelButton("不了，取消", new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View view) {
                        // 不进行任何操作
                        //showCustomDialog(getContext());
                        return false;
                    }
                })
                .show();
    }
    // TODO 自定义布局
    public void showCustomDialog(Context context){
        //对于未实例化的布局：
        CustomDialog.show((AppCompatActivity) context, R.layout.layout_custom_dialog, new CustomDialog.OnBindView() {
            @Override
            public void onBind(final CustomDialog dialog, View v) {
                ImageView btnOk = v.findViewById(R.id.btn_ok);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.doDismiss();
                    }
                });
            }
        });
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
     * 是否需要完善信息
     * @param isInfo true不需要
     */
    private void userIsInfo(boolean isInfo) {
        if (isInfo) {
            // 没有完善信息，提示完善
            showMessageDialogGotoInfo(getContext(),"提示","初次登陆，请先完善您的个人信息，否则将无法使用此软件，是否前往完善？");
        } else {
            // 已经完善信息，不做提示
            return;
        }
    }

    /**
     * 完善信息的弹窗
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public void showMessageDialogGotoInfo(Context context, String title, String content) {
        MessageDialog.build((AppCompatActivity) context)
                .setTitle(title)
                .setMessage(content)
                .setOkButton("去完善", new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View view) {
//                        // 立即前往完善
//                        mFragment = new UpdateInfoFragment();
//                        // 跳转到完善信息的fragment
//                        getFragmentManager().beginTransaction()
//                                .replace(R.id.lay_container,mFragment)
//                                .commit();
//                        SPUtils.put(getContext(),"isInfo",false);
                        // 立即前往完善
                        UpdateInfoActivity.show(getContext());
                        return false;
                    }
                })
                .setCancelButton("退出", new OnDialogButtonClickListener() {
                    @Override
                    public boolean onClick(BaseDialog baseDialog, View view) {
                        // 直接退出app
                        System.exit(0);
                        return false;
                    }
                })
                .show();
    }
}