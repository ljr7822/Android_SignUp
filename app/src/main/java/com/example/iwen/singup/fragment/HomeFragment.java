package com.example.iwen.singup.fragment;

import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.common.widget.GalleryView;
import com.example.iwen.singup.R;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.galleryView)
    GalleryView mGalley;

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
        mGalley.setup(getLoaderManager(), new GalleryView.SelectedChangeListener() {
            @Override
            public void onSelectedCountChanged(int count) {

            }
        });
    }
}