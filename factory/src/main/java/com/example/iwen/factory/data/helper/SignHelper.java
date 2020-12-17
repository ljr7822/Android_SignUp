package com.example.iwen.factory.data.helper;

import android.util.Log;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.api.sign.SignModel;
import com.example.iwen.factory.model.db.sign.SignRspModel;
import com.example.iwen.factory.net.Network;
import com.example.iwen.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 打卡相关网络请求
 *
 * @author : iwen大大怪
 * create : 11-29 029 15:35
 */
public class SignHelper {
    /**
     * 发起打卡
     *
     * @param model    发起打卡时传入的数据模型
     * @param callback 请求成功后的回调
     */
    public static void sign(SignModel model, final DataSource.Callback<SignRspModel> callback) {
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 创建call
        Call<RspModel<SignRspModel>> call = service.sign(model);
        call.enqueue(new Callback<RspModel<SignRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<SignRspModel>> call, Response<RspModel<SignRspModel>> response) {
                RspModel<SignRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    SignRspModel signRspModel = rspModel.getData();
                    callback.onDataLoaded(signRspModel);
                }
            }

            @Override
            public void onFailure(Call<RspModel<SignRspModel>> call, Throwable t) {
                Log.e("ljr", "onFailure:" + t);
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}
