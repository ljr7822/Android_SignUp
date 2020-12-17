package com.example.iwen.factory.data.helper;

import android.util.Log;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.api.account.UserModel;
import com.example.iwen.factory.model.api.user.UserUpdateModel;
import com.example.iwen.factory.model.db.account.PersonalRecordRspModel;
import com.example.iwen.factory.model.db.account.UserRspModel;
import com.example.iwen.factory.net.Network;
import com.example.iwen.factory.net.RemoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 获取用户信息网络请求
 *
 * @author : iwen大大怪
 * create : 12-10 010 19:09
 */
public class UserHelper {
    /**
     * 获取用户信息
     *
     * @param model    UserModel
     * @param callback DataSource.Callback<UserRspModel>
     */
    public static void getUser(UserModel model, final DataSource.Callback<UserRspModel> callback) {
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 创建call
        Call<RspModel<UserRspModel>> call = service.getUser(model);
        call.enqueue(new Callback<RspModel<UserRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<UserRspModel>> call, Response<RspModel<UserRspModel>> response) {
                RspModel<UserRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    UserRspModel userRspModel = rspModel.getData();
                    callback.onDataLoaded(userRspModel);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserRspModel>> call, Throwable t) {
                Log.e("ljr", "onFailure:" + t);
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    /**
     * 获取用户个人打卡记录
     *
     * @param model    UserModel
     * @param callback DataSource.Callback<List<PersonalRecordRspModel>>
     */
    public static void getPersonalRecord(UserModel model, final DataSource.Callback<List<PersonalRecordRspModel>> callback) {
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 创建call
        Call<RspModel<List<PersonalRecordRspModel>>> call = service.getPersonalRecord(model);
        call.enqueue(new Callback<RspModel<List<PersonalRecordRspModel>>>() {
            @Override
            public void onResponse(Call<RspModel<List<PersonalRecordRspModel>>> call, Response<RspModel<List<PersonalRecordRspModel>>> response) {
                RspModel<List<PersonalRecordRspModel>> rspModel = response.body();
                if (rspModel.success()) {
                    List<PersonalRecordRspModel> personalRecordRspModelList = rspModel.getData();
                    callback.onDataLoaded(personalRecordRspModelList);
                }
            }

            @Override
            public void onFailure(Call<RspModel<List<PersonalRecordRspModel>>> call, Throwable t) {
                Log.e("ljr", "onFailure:" + t);
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }

    /**
     * 更新用户信息
     *
     * @param model    UserUpdateModel
     * @param callback DataSource.Callback<UserCard>
     */
    public static void update(UserUpdateModel model, final DataSource.Callback<UserRspModel> callback) {
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 得到一个call进行注册
        Call<RspModel<UserRspModel>> call = service.userUpdate(model);
        // 进行异步请求
        call.enqueue(new Callback<RspModel<UserRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<UserRspModel>> call, Response<RspModel<UserRspModel>> response) {
                RspModel<UserRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    // 回调成功
                    UserRspModel userRspModel = rspModel.getData();
                    callback.onDataLoaded(userRspModel);
                }
            }

            @Override
            public void onFailure(Call<RspModel<UserRspModel>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
                Log.e("ljr", "onFailure message: " + t);
            }
        });
    }
}
