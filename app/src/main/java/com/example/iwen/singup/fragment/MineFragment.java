package com.example.iwen.singup.fragment;

import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.example.iwen.common.app.Application;
import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.singup.R;

import butterknife.BindView;

public class MineFragment extends BaseFragment {
    @BindView(R.id.btn_toast)
    Button mButton;

    public MineFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);
        String[] strings = new String[]{"条目1", "条目2", "条目3", "条目4", "条目5","条目6", "条目7", "条目8", "条目9", "条目10"};
        mButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Application.showCityPickerPopup(getActivity());
            }
        });
    }
}