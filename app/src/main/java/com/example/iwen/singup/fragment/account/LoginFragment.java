package com.example.iwen.singup.fragment.account;

import android.content.Context;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.iwen.common.app.BaseFragment;
import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.common.utils.HashUtil;
import com.example.iwen.common.utils.SPUtils;
import com.example.iwen.factory.model.db.account.LoginRspModel;
import com.example.iwen.factory.presenter.account.LoginContract;
import com.example.iwen.factory.presenter.account.LoginPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.activities.MainActivity;

import net.qiujuer.genius.ui.widget.Loading;

import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * 登录的Fragment
 */
public class LoginFragment
        extends PresenterFragment<LoginContract.Presenter>
        implements LoginContract.View {

    private AccountTrigger mAccountTrigger;
    // 用户信息
    private String workId;
    private String passwordMd5;
    private String mac;
    // 是否完善信息的标志 true表示已经完善，false表示需要完善
    private boolean isInfo;

    private BaseFragment mFragment;

    // 工号
    @BindView(R.id.edt_workid)
    EditText mWorkId;
    // 密码
    @BindView(R.id.edt_password)
    EditText mPassword;
    // 加载
    @BindView(R.id.loading)
    Loading mLoading;
    // 登录按钮
    @BindView(R.id.btn_submit)
    Button mSubmit;
    // 第三方qq登录
    @BindView(R.id.im_qq_icon)
    ImageView mQqIcon;
    // 第三方微信登录
    @BindView(R.id.im_wechar_icon)
    ImageView mWeCharIcon;
    // 第三方微博登录
    @BindView(R.id.im_weibo_icon)
    ImageView mWeiBoIcon;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mAccountTrigger = (AccountTrigger) context;
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_login;
    }

    // 登录按钮点击事件
    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.btn_submit)
    void onSubmitClick() {
        workId = mWorkId.getText().toString();
        String password = mPassword.getText().toString();
        passwordMd5 = passwordToMd5(password);
        mac = (String) SPUtils.get(Objects.requireNonNull(getContext()), "DeviceId", "mac");
        // 调用p层进行登录
        mPresenter.login(workId, password, mac);
    }

    /**
     * 密码MD5加密
     *
     * @param password 原文
     * @return 密文
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String passwordToMd5(String password) {
        // 去除首尾空格
        password = password.trim();
        // 进行MD5加密
        password = HashUtil.getMD5String(password);
        // 在进行一次Base64加密
        return HashUtil.encodeBase64(password);
    }

    // qq登录按钮点击事件
    @OnClick(R.id.im_qq_icon)
    void onQqClick() {
        Toasty.warning(Objects.requireNonNull(getContext()), "程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
    }

    // 微信登录按钮点击事件
    @OnClick(R.id.im_wechar_icon)
    void onWeCharClick() {
        Toasty.warning(Objects.requireNonNull(getContext()), "程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
    }

    // 微博登录按钮点击事件
    @OnClick(R.id.im_weibo_icon)
    void onWeiBoClick() {
        Toasty.warning(Objects.requireNonNull(getContext()), "程序员小哥哥正在秃头开发中...", Toast.LENGTH_SHORT, true).show();
    }

    // 跳转至注册界面
    @OnClick(R.id.tv_go_register)
    void onShowLoginClick() {
        // 让AccountTrigger进行界面切换
        // mAccountTrigger.triggerView();
        Toasty.warning(Objects.requireNonNull(getContext()), "暂无权限，请先登录>._.< ", Toast.LENGTH_SHORT, true).show();
    }

    @Override
    public void showError(int str) {
        super.showError(str);
        // 当提示需要显示错误的时候触发，一定是结束了
        // 停止loading
        mLoading.stop();
        // 让控件可以输入
        mWorkId.setEnabled(true);
        mPassword.setEnabled(true);
        // 提交按钮可以继续点击
        mSubmit.setEnabled(true);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        // 正在进行时，界面不可操作
        // 开始loading
        mLoading.start();
        // 让控件不可以输入
        mWorkId.setEnabled(false);
        mPassword.setEnabled(false);
        // 提交按钮不可以继续点击
        mSubmit.setEnabled(false);
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void loginSuccess(LoginRspModel loginRspModel) {
        // 注册成功，这时账户已经登录，判断是否已经完善用户信息，没有完成就跳转完成用户信息界面
        // 否则进行跳转到MainActivity界面
        //isInfo = (boolean) SPUtils.get(getContext(),"isInfo",false);
        MainActivity.show(getContext());
        // 进行数据持久化，将数据保存到Xml文件中
        SPUtils.put(Objects.requireNonNull(getContext()), "workId", workId);
        SPUtils.put(Objects.requireNonNull(getContext()), "password", passwordMd5);
        SPUtils.put(Objects.requireNonNull(getContext()), "isLogin", true);
        SPUtils.put(Objects.requireNonNull(getContext()), "name", loginRspModel.getName());
        //SPUtils.put(Objects.requireNonNull(getContext()), "departmentName", loginRspModel.getDepartmentName());
        SPUtils.put(Objects.requireNonNull(getContext()), "departmentId", loginRspModel.getDepartmentId());
        //SPUtils.put(Objects.requireNonNull(getContext()), "icon", loginRspModel.getIcon());
        SPUtils.put(Objects.requireNonNull(getContext()), "phoneNumber", loginRspModel.getPhoneNumber());
        // 关闭当前界面
        getActivity().finish();
    }
}
