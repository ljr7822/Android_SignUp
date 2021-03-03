package com.example.iwen.factory.data.helper;

import android.util.Log;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.api.sign.InSignUpModel;
import com.example.iwen.factory.model.api.sign.SignModel;
import com.example.iwen.factory.model.db.sign.InSignRspModel;
import com.example.iwen.factory.model.db.sign.SignRspModel;
import com.example.iwen.factory.net.Network;
import com.example.iwen.factory.net.RemoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author : iwen大大怪
 * create : 2020-12-22 13:47
 */
public class InSignUpHelper {
    /**
     * 获取打卡任务的信息
     *
     * @param model    发起打卡时传入的数据模型
     * @param callback 请求成功后的回调
     */
    public static void InSign(InSignUpModel model, final DataSource.Callback<InSignRspModel> callback) {
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 创建call
        Call<RspModel<InSignRspModel>> call = service.InSign(model);
        call.enqueue(new Callback<RspModel<InSignRspModel>>() {
            @Override
            public void onResponse(Call<RspModel<InSignRspModel>> call, Response<RspModel<InSignRspModel>> response) {
                RspModel<InSignRspModel> rspModel = response.body();
                if (rspModel.success()) {
                    InSignRspModel inSignRspModel = rspModel.getData();
                    callback.onDataLoaded(inSignRspModel);
                }else if (rspModel.getCode()!= 0){
                    callback.onDataNotAvailable(R.string.data_sign_error);
                }
            }

            @Override
            public void onFailure(Call<RspModel<InSignRspModel>> call, Throwable t) {
                Log.e("ljr", "onFailure:" + t);
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}
