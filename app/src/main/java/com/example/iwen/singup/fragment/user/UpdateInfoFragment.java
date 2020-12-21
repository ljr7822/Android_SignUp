package com.example.iwen.singup.fragment.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.iwen.common.app.Application;
import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.common.utils.HashUtil;
import com.example.iwen.common.widget.PortraitView;
import com.example.iwen.factory.Factory;
import com.example.iwen.factory.net.UploadHelper;
import com.example.iwen.factory.presenter.user.UpdateInfoContract;
import com.example.iwen.factory.presenter.user.UpdateInfoPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.activities.AccountActivity;
import com.example.iwen.singup.activities.MainActivity;
import com.example.iwen.singup.fragment.media.GalleryFragment;
import com.yalantis.ucrop.UCrop;

import net.qiujuer.genius.ui.widget.Loading;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * 修改用户信息的fragment
 *
 * @author : Iwen大大怪
 * create : 2020/11/15 9:05
 */
public class UpdateInfoFragment
        extends PresenterFragment<UpdateInfoContract.Presenter>
        implements UpdateInfoContract.View {
    // 头像
    @BindView(R.id.im_portrait)
    PortraitView mPortraitView;
    // 部门
    @BindView(R.id.edt_department)
    EditText mDepartment;
    // 性别
    @BindView(R.id.iv_sex_man)
    ImageView mSex;
    // 姓名
    @BindView(R.id.edt_name)
    EditText mName;
    // 手机号
    @BindView(R.id.edt_phone)
    EditText mPhone;
    // 密码
    @BindView(R.id.edt_password)
    EditText mPassword;
    // 等待
    @BindView(R.id.loading)
    Loading mLoading;
    // 提交
    @BindView(R.id.btn_submit)
    Button mSubmit;
    // 存储头像本地路径
    private String mPortraitPath;
    // 默认为男生
    private boolean isMan = true;

    public UpdateInfoFragment() {
        // Required empty public constructor
    }

    /**
     * UpdateInfoFragment的显示入口
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        Intent intent = new Intent(context, UpdateInfoFragment.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_update_info;
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
        mPortraitPath = uri.getPath();
        // 拿到本地地址
        Glide.with(getContext())
                .load(uri)
                .centerCrop()
                .into(mPortraitView);

        // 拿到本地文件地址
        final String localPath = uri.getPath();
        Log.e("TAG", "localPath" + localPath);

        // 使用线程池，将图片上传到oss文件夹
        Factory.runOnAsync(new Runnable() {
            @Override
            public void run() {
                String url = UploadHelper.uploadImage(localPath);
                Log.e("TAG", "url" + url);
            }
        });
    }

    @Override
    protected UpdateInfoContract.Presenter initPresenter() {
        return new UpdateInfoPresenter(this);
    }

    @Override
    public void UpdateSuccess() {
        MainActivity.show(getContext());
        getActivity().finish();
    }

    /**
     * 提交按钮点击触发
     */
    @OnClick(R.id.btn_submit)
    void onSubmitClick() {
        String department = mDepartment.getText().toString();
        String name = mName.getText().toString();
        String phone = mPhone.getText().toString();
        String passwordMd5 = HashUtil.getMD5String(mPassword.getText().toString());
        // 调用p层进行注册
        mPresenter.update(getContext(), mPortraitPath, department, name, passwordMd5, phone, 1);
    }

    /**
     * 性别图标点击触发
     */
    @OnClick(R.id.iv_sex_man)
    void onSexClick() {
        isMan = !isMan;
        Drawable drawable = getResources().getDrawable(isMan ? R.drawable.ic_sex_man : R.drawable.ic_sex_woman);
        mSex.setImageDrawable(drawable);
        // 设置背景层级切换颜色
        mSex.getBackground().setLevel(isMan ? 0 : 1);
    }

    @Override
    public void showError(int str) {
        super.showError(str);
        // 停止loading
        mLoading.stop();
        mDepartment.setEnabled(true);
        mPortraitView.setEnabled(true);
        mSex.setEnabled(true);
        mSubmit.setEnabled(true);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        mDepartment.setEnabled(false);
        mPortraitView.setEnabled(false);
        mSex.setEnabled(false);
        mSubmit.setEnabled(false);
    }
}
