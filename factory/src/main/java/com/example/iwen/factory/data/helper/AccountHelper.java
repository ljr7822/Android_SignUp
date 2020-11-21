package com.example.iwen.factory.data.helper;

import android.util.Log;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.api.account.RegisterModel;
import com.example.iwen.factory.model.api.account.LoginModel;
import com.example.iwen.factory.model.api.account.UserRspModel;
import com.example.iwen.factory.model.db.User;
import com.example.iwen.factory.net.Network;
import com.example.iwen.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 账户相关的具体操作
 * author : Iwen大大怪
 * create : 2020/11/17 11:13
 */
public class AccountHelper {

    /**
     * 注册的接口，异步调用
     *
     * @param model    传递一个注册的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void register(RegisterModel model, final DataSource.Callback<User> callback) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                callback.onDataNotAvailable(R.string.data_rsp_error_parameters);
            }
        }.start();
    }

    /**
     * 登录的接口
     *
     * @param model    传递一个登录的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void login(final LoginModel model, final DataSource.Callback<UserRspModel> callback) {
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 创建call
        Call<RspModel<UserRspModel>> call = service.userLogin(model);
        // 发起请求
        call.enqueue(new Callback<RspModel<UserRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<UserRspModel>> call, Response<RspModel<UserRspModel>> response) {
                // 先取出第一层数据
                RspModel<UserRspModel> rspModel = response.body();
                if (rspModel.success()){
                    // 判断状态码，再取出第二层数据
                    UserRspModel userRspModel = rspModel.getData();
                    Log.e("ljr","第二层"+rspModel.getData());
                    // 回调一个用户数据
                    callback.onDataLoaded(userRspModel);
                }
            }
            @Override
            public void onFailure(Call<RspModel<UserRspModel>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
                Log.e("ljr","onFailure"+t);
            }
        });
    }
}