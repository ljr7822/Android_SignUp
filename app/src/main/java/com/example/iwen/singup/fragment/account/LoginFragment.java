package com.example.iwen.singup.fragment.account;

import android.content.Context;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.iwen.common.Common;
import com.example.iwen.common.app.Application;
import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.common.utils.HashUtil;
import com.example.iwen.factory.model.api.account.LoginModel;
import com.example.iwen.factory.okhttp.OkHttpSSH;
import com.example.iwen.factory.presenter.account.LoginContract;
import com.example.iwen.factory.presenter.account.LoginPresenter;
import com.example.iwen.singup.R;
import com.example.iwen.singup.activities.MainActivity;
import com.google.gson.Gson;

import net.qiujuer.genius.ui.widget.Loading;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 登录的Fragment
 */
public class LoginFragment
        extends PresenterFragment<LoginContract.Presenter>
        implements LoginContract.View {
    private AccountTrigger mAccountTrigger;

    // 工号
    @BindView(R.id.edt_workid)
    EditText mWorkId;
    // 密码
    @BindView(R.id.edt_password)
    EditText mPassword;
    // 加载
    @BindView(R.id.loading)
    Loading mLoading;
    // 按钮
    @BindView(R.id.btn_submit)
    Button mSubmit;

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
    @OnClick(R.id.btn_submit)
    void onSubmitClick() {
        String workId = mWorkId.getText().toString();
        String password = mPassword.getText().toString();
        // 调用p层进行登录
        // TODO 登录逻辑入口
        mPresenter.login(workId, password);
        // TODO 测试
        // MainActivity.show(getContext());
        //PostLogin(workId, password);

    }

    // 跳转至注册界面
    @OnClick(R.id.tv_go_register)
    void onShowLoginClick() {
        // 让AccountTrigger进行界面切换
        mAccountTrigger.triggerView();
    }

    /**
     * 进行一次切换
     * 这个方法在哪个Fragment里面复写，接切换到另一个fragment
     */
//    @Override
//    public void onResume() {
//        super.onResume();
//        mAccountTrigger.triggerView();
//    }

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
    public void loginSuccess() {
        // 注册成功，这时账户已经登录，进行跳转到MainActivity界面
        MainActivity.show(getContext());
        // 关闭当前界面
        getActivity().finish();
    }

    /**
     * 登录请求
     *
     * @param workId   工号
     * @param password 密码
     */
    private void PostLogin(String workId, String password) {
        // 将密码转化为MD5
        String Md5Password = HashUtil.getMD5String(password);
        Log.e("ljr", "MD5:" + Md5Password);
        // 创建一个okhttp客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(50000, TimeUnit.MILLISECONDS)
                // 跳过https证书
                .sslSocketFactory(OkHttpSSH.createSSLSocketFactory(), new OkHttpSSH.TrustAllCerts())
                .build();
        // 创建请求体model
        LoginModel loginModel = new LoginModel(workId, password);
        // 转化为json格式
        Gson gson = new Gson();
        String json = gson.toJson(loginModel);
        // 设置mediaType
        MediaType mediaType = MediaType.parse("application/json");
        // 封装请求体
        RequestBody requestBody = RequestBody.create(mediaType, json);
        // 请求
        Request request = new Request.Builder()
                .addHeader("Connection", "close")
                .post(requestBody)
                .url(Common.Constance.API_URL)
                .build();
        // 发起请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Application.showToast("请求失败");
                Log.e("ljr", "onResponse:" + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("ljr", "onResponse:" + response.code());
                Log.e("ljr", "onResponse:" + response.body().string());
            }
        });
    }

    /**
     * Map<String, String> map = new HashMap<>();
     *         map.put("workId", workid);
     *         map.put("password", password);
     *         StringBuilder stringBuilder = new StringBuilder();
     *         stringBuilder.append("?");
     *         Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
     *         while (iterator.hasNext()) {
     *             Map.Entry<String, String> next = iterator.next();
     *             stringBuilder.append(next.getKey());
     *             stringBuilder.append("=");
     *             stringBuilder.append(next.getValue());
     *             if (iterator.hasNext()) {
     *                 stringBuilder.append("&");
     *             }
     *         }
     *         String str = stringBuilder.toString();
     */
}
