package com.example.iwen.singup.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwen.common.utils.DateTimeUtil;
import com.example.iwen.common.widget.MessageItemTouchHelperCallback;
import com.example.iwen.common.widget.SpacesItemDecoration;
import com.example.iwen.common.widget.message.MessageAdapter;
import com.example.iwen.common.widget.message.Messages;
import com.example.iwen.factory.model.db.LoginRspModel;
import com.example.iwen.factory.presenter.location.LocationContract;
import com.example.iwen.singup.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息页面fragment
 */
public class ContactFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Messages> mMessagesListList = new ArrayList<>();
    private MessageAdapter mMessageAdapter;
    private ItemTouchHelper mItemTouchHelper;

    protected LocationContract.Presenter mPresenter;

    private LoginRspModel mLoginRspModel;

    public ContactFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);

        LinearLayoutManager layout = new LinearLayoutManager(getContext());
        recyclerView = rootView.findViewById(R.id.recycler_view);
        mMessageAdapter = new MessageAdapter(mMessagesListList, getActivity());
        recyclerView.setLayoutManager(layout);
        mMessagesListList = generateDummyList(10);
        recyclerView.addItemDecoration(new SpacesItemDecoration(0));
        ItemTouchHelper.Callback callback = new MessageItemTouchHelperCallback(mMessageAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        recyclerView.setAdapter(mMessageAdapter);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // 模拟数据
    private List<Messages> generateDummyList(int size){
        mMessagesListList = new ArrayList<Messages>();
        for (int i = 0;i<size;i++){
            int drawable = R.mipmap.bg_src_lu;
            Messages messages = new Messages("晚间查寝",
                    "西南大学商贸学院2020级晚间查寝",
                    "2020-11-24",
                    "19:00",
                    "未签到",i,drawable);
            mMessagesListList.add(messages);
        }
        return mMessagesListList;
    }

}