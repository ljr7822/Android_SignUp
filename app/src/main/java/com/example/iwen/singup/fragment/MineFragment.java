package com.example.iwen.singup.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.iwen.common.app.Application;
import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.common.widget.PortraitView;
import com.example.iwen.factory.model.db.account.UserRspModel;
import com.example.iwen.factory.presenter.account.UserContract;
import com.example.iwen.factory.presenter.account.UserPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.activities.DescActivity;
import com.example.iwen.singup.activities.MainActivity;
import com.example.iwen.singup.activities.RecordActivity;
import com.example.iwen.singup.activities.SettingActivity;
import com.example.iwen.singup.activities.SignActivity;
import com.example.iwen.singup.fragment.media.GalleryFragment;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class MineFragment
        extends PresenterFragment<UserContract.Presenter>
        implements UserContract.View {
    // 头像
    @BindView(R.id.im_portrait)
    PortraitView mPortraitViewMine;
    // 签到记录
    @BindView(R.id.sign_record)
    LinearLayout mRecordLay;
    // 统计信息
    @BindView(R.id.statistical)
    LinearLayout mStatisticalLay;
    // 任务中心
    @BindView(R.id.task)
    LinearLayout mTaskLay;
    // 设置
    @BindView(R.id.seeting)
    LinearLayout mMessageLay;
    // 名称
    @BindView(R.id.user_name)
    TextView mUserName;
    // 部门
    @BindView(R.id.user_department)
    TextView mDepartment;
    // 存储头像本地路径
    private String mPortraitPath;

    // 全局workId
    private String workId;

    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * 绑定layout
     */
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);

    }

    /**
     * 头像点击事件
     */
    @OnClick(R.id.im_portrait)
    void onPortraitViewClick() {
        new GalleryFragment().setListener(new GalleryFragment.OnSelectedListener() {
            @Override
            public void onSelectedImage(String path) {
                UCrop.Options options = new UCrop.Options();
                // 设置图片处理格式
                options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
                // 设置压缩后图片的精度
                options.setCompressionQuality(96);
                // 得到头像缓存地址
                File dPath = Application.getAvatarTempFile();
                // 发起剪切
                UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(dPath))
                        .withAspectRatio(1, 1) // 1:1比例
                        .withMaxResultSize(520, 520) // 最大尺寸
                        .withOptions(options)
                        .start(getActivity());
            }
        }).show(getChildFragmentManager(), GalleryFragment.class.getName());
    }

    // 收到从Activity传过来的回调，取出其中的值进行图片加载
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 是当前fragment能够处理的类型
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            // 获取uri进行加载
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri != null) {
                loadAvatar(resultUri);
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            Application.showToast(R.string.data_rsp_error_unknown);
            // final Throwable cropError = UCrop.getError(data);
        }
    }

    /**
     * 加载uri到头像中
     *
     * @param uri 图片uri
     */
    private void loadAvatar(Uri uri) {
        // 得到头像地址
        mPortraitPath = uri.getPath();
        // 拿到本地地址
        Glide.with(getContext())
                .load(uri)
                .centerCrop()
                .into(mPortraitViewMine);

//        // 拿到本地文件地址
//        final String localPath = uri.getPath();
//        Log.e("ljr", "localPath" + localPath);
//
//        // 使用线程池，将图片上传到oss文件夹
//        Factory.runOnAsync(new Runnable() {
//            @Override
//            public void run() {
//                String url = UploadHelper.uploadAvatar(localPath);
//                Log.e("ljr", "url" + url);
//            }
//        });
    }

    /**
     * 签到记录点击事件
     */
    @OnClick(R.id.sign_record)
    void onRecordLayClick() {
//        mPersonalRecordFragment = new ContactFragment();
//        Objects.requireNonNull(getActivity()).getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.lay_container, mPersonalRecordFragment)
//                .commit();
//        PersonalRecordActivity.show(getContext());
        //Toasty.warning(Objects.requireNonNull(getContext()),"程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
        // 跳转签到记录的Activity
        Intent intent = new Intent(getContext(), RecordActivity.class);
        startActivity(intent);
    }

    /**
     * 统计信息点击事件
     */
    @OnClick(R.id.statistical)
    void onStatisticalLayClick() {
        Toasty.warning(Objects.requireNonNull(getActivity()), "程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 任务点击事件
     */
    @OnClick(R.id.task)
    void onTaskLayClick() {
        Toasty.warning(Objects.requireNonNull(getActivity()), "程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 设置点击事件
     */
    @OnClick(R.id.seeting)
    void onSettingLayClick() {
        SettingActivity.show(getContext());
    }

    /**
     * 初始化
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        workId = (String) SPUtils.get(Objects.requireNonNull(getContext()),"workId","10010002");
        // 调用p层获取
        mPresenter.userCard(workId);
    }



    /**
     * 初始化Presenter
     */
    @Override
    protected UserContract.Presenter initPresenter() {
        return new UserPresenter(this);
    }

    @Override
    public void userCardSuccess(UserRspModel userRspModel) {

        // String resultUri = (String) SPUtils.get(getContext(),"resultUri","111");
        String resultUri = userRspModel.getIcon();
        Glide.with(this)
                .load(resultUri)
                .centerCrop()
                .into(mPortraitViewMine);
        mUserName.setText(userRspModel.getName());
        mDepartment.setText(userRspModel.getDepartmentName());
    }
}