package com.example.iwen.singup.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

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
import com.example.iwen.singup.fragment.media.GalleryFragment;
import com.example.iwen.singup.fragment.user.UpdateInfoFragment;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class MineFragment extends BaseFragment {
//    // 头像
//    @BindView(R.id.im_portrait_mine)
//    PortraitView mPortraitViewMine;

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

//    /**
//     * 头像点击事件
//     */
//    @OnClick(R.id.im_portrait_mine)
//    void onPortraitViewClick() {
//        new GalleryFragment().setListener(new GalleryFragment.OnSelectedListener() {
//            @Override
//            public void onSelectedImage(String path) {
//                UCrop.Options options = new UCrop.Options();
//                // 设置图片处理格式
//                options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
//                // 设置压缩后图片的精度
//                options.setCompressionQuality(96);
//                // 得到头像缓存地址
//                File dPath = Application.getAvatarTempFile();
//                // 发起剪切
//                UCrop.of(Uri.fromFile(new File(path)), Uri.fromFile(dPath))
//                        .withAspectRatio(1, 1) // 1:1比例
//                        .withMaxResultSize(520, 520) // 最大尺寸
//                        .withOptions(options)
//                        .start(getActivity());
//            }
//        }).show(getChildFragmentManager(), GalleryFragment.class.getName());
//    }
//    // 收到从Activity传过来的回调，取出其中的值进行图片加载
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // 是当前fragment能够处理的类型
//        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
//            // 获取uri进行加载
//            final Uri resultUri = UCrop.getOutput(data);
//            if (resultUri != null) {
//                loadAvatar(resultUri);
//            }
//        } else if (resultCode == UCrop.RESULT_ERROR) {
//            final Throwable cropError = UCrop.getError(data);
//        }
//    }
//    /**
//     * 加载uri到头像中
//     *
//     * @param uri 图片uri
//     */
//    private void loadAvatar(Uri uri) {
//        // 得到头像地址
//        avatarUrl = uri.getPath();
//        // 拿到本地地址
//        Glide.with(getContext())
//                .load(uri)
//                .centerCrop()
//                .into(mPortraitViewMine);
//
//        // 拿到本地文件地址
//        final String localPath = uri.getPath();
//        Log.e("TAG", "localPath" + localPath);
//
//        // 使用线程池，将图片上传到oss文件夹
//        Factory.runOnAsync(new Runnable() {
//            @Override
//            public void run() {
//                String url = UploadHelper.uploadAvatar(localPath);
//                Log.e("TAG", "url" + url);
//            }
//        });
//    }
}