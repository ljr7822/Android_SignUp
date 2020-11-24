package com.example.iwen.singup.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.example.iwen.common.app.Application;
import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.common.utils.DeviceIdUtil;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.common.widget.PortraitView;
import com.example.iwen.factory.Factory;
import com.example.iwen.factory.net.UploadHelper;
import com.example.iwen.singup.R;
import com.example.iwen.singup.activities.SettingActivity;
import com.example.iwen.singup.fragment.media.GalleryFragment;
import com.example.iwen.singup.fragment.user.UpdateInfoFragment;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends BaseFragment {
    //    private LinearLayout mRecordLay;
//    private LinearLayout mStatisticalLay;
//    private LinearLayout mTaskLay;
//    private LinearLayout mMessageLay;
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

    private String avatarUrl;

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
            final Throwable cropError = UCrop.getError(data);
        }
    }

    /**
     * 加载uri到头像中
     *
     * @param uri 图片uri
     */
    private void loadAvatar(Uri uri) {
        // 得到头像地址
        avatarUrl = uri.getPath();
        // 拿到本地地址
        Glide.with(getContext())
                .load(uri)
                .centerCrop()
                .into(mPortraitViewMine);

        // 拿到本地文件地址
        final String localPath = uri.getPath();
        Log.e("TAG", "localPath" + localPath);

        // 使用线程池，将图片上传到oss文件夹
        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                String url = UploadHelper.uploadAvatar(localPath);
                Log.e("TAG", "url" + url);
            }
        });
    }

    /**
     * 签到记录点击事件
     */
    @OnClick(R.id.sign_record)
    void onRecordLayClick() {
        Toasty.warning(getContext(), "程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 统计信息点击事件
     */
    @OnClick(R.id.statistical)
    void onStatisticalLayClick() {
        Toasty.warning(getContext(), "程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 任务点击事件
     */
    @OnClick(R.id.task)
    void onTaskLayClick() {
        Toasty.warning(getContext(), "程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
    }

    /**
     * 设置点击事件
     */
    @OnClick(R.id.seeting)
    void onSettingLayClick() {
        SettingActivity.show(getContext());
    }
}