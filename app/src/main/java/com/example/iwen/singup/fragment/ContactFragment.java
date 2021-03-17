package com.example.iwen.singup.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.common.utils.DateTimeUtil;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.common.widget.EmptyView;
import com.example.iwen.common.widget.recycler.RecyclerAdapter;
import com.example.iwen.factory.model.db.location.LocationTaskList;
import com.example.iwen.factory.presenter.location.GetLocationTaskListContract;
import com.example.iwen.factory.presenter.location.GetLocationTaskListPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.activities.DescActivity;
import com.example.iwen.singup.activities.MainActivity;
import com.example.iwen.singup.activities.SignActivity;

import net.qiujuer.genius.res.Resource;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

/**
 * 消息页面fragment
 *
 * @author : Iwen大大怪
 * create : 2020/11/16 9:59
 */
public class ContactFragment
        extends PresenterFragment<GetLocationTaskListContract.Presenter>
        implements GetLocationTaskListContract.View {

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    @BindView(R.id.recycler_view)
    RecyclerView mRecycler;

    private RecyclerAdapter<LocationTaskList> mAdapter;

    // 当前系统时间
    private String systemTime;
    // 格式化后的系统时间:yyyy-mm-dd
    private String formatTimeDay;
    // 格式化后的系统时间:12:12:11
    private String formatTimeH;
    // 全局workId
    private String workId;
    //记录当前item位置
    private int currentPosition = 0;

    public ContactFragment() {
    }

    /**
     * 绑定布局
     */
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_contact;
    }

    /**
     * 初始化控件
     *
     * @param view 根视图
     */
    @Override
    protected void initWidget(View view) {
        super.initWidget(view);
        // 初始化适配器
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 设置Adapter
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<LocationTaskList>() {
            @Override
            protected int getItemViewType(int position, LocationTaskList locationTaskList) {
                // 返回cell的布局id
                return R.layout.item_message;
            }

            @Override
            protected ViewHolder<LocationTaskList> onCreateViewHolder(View root, int viewType) {
                return new ContactFragment.ViewHolder(root);
            }
        });
        // 设置点击监听事件
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<LocationTaskList>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, LocationTaskList locationTaskList) {
                // 先判断是否已经签到，如果已经签到，就直接跳转到签到详情
                if (locationTaskList.getIfSign().equals("已签到")) {
                    Intent intent = new Intent(getActivity(), DescActivity.class);
                    intent.putExtra("department", locationTaskList.getDepartmentName()+"  "+locationTaskList.getCollectName());
                    intent.putExtra("date", locationTaskList.getDate() + " " + locationTaskList.getTime());
                    intent.putExtra("adder", locationTaskList.getAddress());
                    intent.putExtra("info", locationTaskList.getCollectInfo()+locationTaskList.getInfo());
                    startActivity(intent);
                } else {
                    // 未签到就前往签到
                    SignActivity.show(getActivity(), locationTaskList);
                }
            }
        });
        // 初始化占位布局
        mEmptyView.bind(mRecycler);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DateTimeUtil dateTimeUtil = new DateTimeUtil();
        systemTime = dateTimeUtil.getTime();
        formatTimeDay = systemTime.split(" ")[0].trim();
        formatTimeH = systemTime.split(" ")[1].trim();
        workId = (String) SPUtils.get(Objects.requireNonNull(getActivity()), "workId", "10010002");
        // 调用p层进行获取
        mPresenter.getLocationTaskList(workId, formatTimeDay);
    }

    /**
     * 获取成功，刷新列表数据
     *
     * @param locationTaskLists 返回的数据列表
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void getLocationTaskListSuccess(List<LocationTaskList> locationTaskLists) {
        // 将传递进来的数据进行排序
        locationTaskLists.sort(new Comparator<LocationTaskList>() {
            @Override
            public int compare(LocationTaskList o1, LocationTaskList o2) {
                DateTimeUtil dateTimeUtil = new DateTimeUtil();
                try {
                    return (int)(dateTimeUtil.getDateLong(o2.getDate())-dateTimeUtil.getDateLong(o1.getDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
        mAdapter.replace(locationTaskLists);
    }

    /**
     * 初始化Presenter
     */
    @Override
    protected GetLocationTaskListContract.Presenter initPresenter() {
        return new GetLocationTaskListPresenter(this);
    }

    /**
     * 每一个cell的布局操作
     */
    static class ViewHolder extends RecyclerAdapter.ViewHolder<LocationTaskList> {
        // 时间
        @BindView(R.id.message_date)
        AppCompatTextView mMessageDate;
        // 标题
        @BindView(R.id.message_title)
        AppCompatTextView mMessageTitle;
        // 详情
        @BindView(R.id.message_desc)
        AppCompatTextView mMessageDesc;
        // 是否签到
        @BindView(R.id.isTag)
        AppCompatTextView mIsTag;
        // 背景
        @BindView(R.id.card_bg)
        ImageView mBg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(LocationTaskList locationTaskList) {
            // 绑定时间
            mMessageDate.setText(locationTaskList.getDate() + " " + locationTaskList.getTime());
            // 绑定标题
            mMessageTitle.setText(locationTaskList.getDepartmentName()+"  "+locationTaskList.getCollectName());
            // 绑定详情
            mMessageDesc.setText(locationTaskList.getAddress());
            // 绑定是否签到
            mIsTag.setText(locationTaskList.getIfSign());
            // 设置字体颜色
            if (locationTaskList.getIfSign().equals("已签到")) {
                mIsTag.setTextColor(Resource.Color.GREEN);
            } else {
                mIsTag.setTextColor(Resource.Color.RED);
            }
        }
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
//        LinearLayoutManager layout = new LinearLayoutManager(getContext());
//        recyclerView = rootView.findViewById(R.id.recycler_view);
//        mMessageRecyclerViewAdapter = new MessageRecyclerViewAdapter(mLocationTaskLists,getActivity());
//        recyclerView.setLayoutManager(layout);
//        recyclerView.setAdapter(mMessageRecyclerViewAdapter);
//        return rootView;
//    }
}