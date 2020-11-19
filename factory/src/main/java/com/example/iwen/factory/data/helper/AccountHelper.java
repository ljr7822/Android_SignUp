package com.example.iwen.factory.data.helper;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.Data;
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
    public static void login(final LoginModel model, final DataSource.Callback<User> callback) {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                callback.onDataNotAvailable(R.string.data_rsp_error_parameters);
//            }
//        }.start();
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        Call<RspModel<UserRspModel>> call = service.userLogin(model);
        call.enqueue(new Callback<RspModel<UserRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<UserRspModel>> call, Response<RspModel<UserRspModel>> response) {
                RspModel<UserRspModel> rspModel = response.body();
                if (rspModel.getCode() == 0) {
                    UserRspModel userRspModel = rspModel.getResult();
                    User user = userRspModel.getUser();
                    callback.onDataLoaded(user);
                } else {
                    callback.onDataNotAvailable(R.string.data_rsp_error_parameters);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserRspModel>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}