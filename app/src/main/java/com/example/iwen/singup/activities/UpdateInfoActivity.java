package com.example.iwen.singup.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideContext;
import com.example.iwen.common.app.Application;
import com.example.iwen.common.app.BaseActivity;
import com.example.iwen.common.app.PresenterActivity;
import com.example.iwen.common.utils.HashUtil;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.common.widget.PortraitView;
import com.example.iwen.factory.Factory;
import com.example.iwen.factory.data.database.userDataBase.UserDao;
import com.example.iwen.factory.data.database.userDataBase.UserDataBase;
import com.example.iwen.factory.data.database.userUpdateDataBase.UserUpdateDao;
import com.example.iwen.factory.data.database.userUpdateDataBase.UserUpdateDataBase;
import com.example.iwen.factory.data.database.userUpdateDataBase.UserUpdateDateModel;
import com.example.iwen.factory.model.api.user.UserUpdateModel;
import com.example.iwen.factory.model.db.account.UserRspModel;
import com.example.iwen.factory.net.UploadHelper;
import com.example.iwen.factory.presenter.user.UpdateInfoContract;
import com.example.iwen.factory.presenter.user.UpdateInfoPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.fragment.media.GalleryFragment;
import com.yalantis.ucrop.UCrop;

import net.qiujuer.genius.ui.widget.Loading;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class UpdateInfoActivity
        extends PresenterActivity<UpdateInfoContract.Presenter>
        implements UpdateInfoContract.View {
    // 全局数据库
    public UserUpdateDataBase userUpdateDataBase;
    public UserUpdateDao userUpdateDao;
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

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_update_info;
    }

    /**
     * UpdateInfoActivity显示入口
     * intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     *
     * @param context 上下文
     */
    public static void show(Context context) {
        Intent intent = new Intent(context, UpdateInfoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化userUpdateDataBase
        userUpdateDataBase = Room.databaseBuilder(getApplicationContext(), UserUpdateDataBase.class, "user_update_database")
                .build();
        // 初始化userUpdateDao
        userUpdateDao = userUpdateDataBase.getUserUpdateDao();
    }

    /**
     * 头像点击事件:弹出选择图片列表
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
                        .start(UpdateInfoActivity.this);
            }
        }).show(getSupportFragmentManager(), GalleryFragment.class.getName());
    }

    // 收到从Activity传过来的回调，取出其中的值进行图片加载
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            // 获取uri进行加载
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri != null) {
                loadAvatar(resultUri);
                // 将头像写入持久化
                SPUtils.put(this, "resultUri", resultUri);
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
        Glide.with(this)
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
                String url = UploadHelper.uploadAvatar(localPath);
                Log.e("TAG", "url" + url);
            }
        });
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

    /**
     * 提交按钮点击触发
     */
    @OnClick(R.id.btn_submit)
    void onSubmitClick() {
        // 保存相关信息
        String department = mDepartment.getText().toString();
        String name = mName.getText().toString();
        String phone = mPhone.getText().toString();
        String passwordMd5 = HashUtil.getMD5String(mPassword.getText().toString());
        // TODO 调用p层进行更新
        mPresenter.update(this, mPortraitPath, department, name, passwordMd5, phone, isMan ? 1 : 0);
    }

    // 初始化Presenter
    @Override
    protected UpdateInfoContract.Presenter initPresenter() {
        return new UpdateInfoPresenter(this);
    }

    @Override
    public void UpdateSuccess(UserRspModel userRspModel) {
        // 将回调的数据写入数据库
//        UserUpdateDateModel userUpdateDateModel = new UserUpdateDateModel(
//                userRspModel.getName(), userRspModel.getWorkId(), userRspModel.getDepartmentName()
//                , userRspModel.getIcon(), userRspModel.getPhoneNumber());
//        userUpdateDao.insertUserUpdate(userUpdateDateModel);
        // 将完善信息的标志设置为false,下次进入就不要再去更新信息了
        //SPUtils.put(this, "isInfo", false);
        MainActivity.show(this);
        this.finish();
    }
}