package com.example.iwen.factory.data.helper;

import android.util.Log;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.Location.GetPersonLocationModel;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.db.location.LocationTaskList;
import com.example.iwen.factory.net.Network;
import com.example.iwen.factory.net.RemoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 定位相关网络请求
 *
 * @author : iwen大大怪
 * create : 11-27 027 14:21
 */
public class LocationHelper {
    /**
     * 获取定位任务列表
     *
     * @param model    传递一个用户和时间的model
     * @param callback 成功与失败的接口回调
     */
    public static void getTaskList(GetPersonLocationModel model, final DataSource.Callback<List<LocationTaskList>> callback) {
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 创建call
        Call<RspModel<List<LocationTaskList>>> call = service.getPerson(model);
        call.enqueue(new Callback<RspModel<List<LocationTaskList>>>() {
            @Override
            public void onResponse(Call<RspModel<List<LocationTaskList>>> call, Response<RspModel<List<LocationTaskList>>> response) {
                RspModel<List<LocationTaskList>> rspModel = response.body();
                if (rspModel.success()) {
                    List<LocationTaskList> mLocationTaskLists = rspModel.getData();
                    callback.onDataLoaded(mLocationTaskLists);
                }
            }

            @Override
            public void onFailure(Call<RspModel<List<LocationTaskList>>> call, Throwable t) {
                Log.e("ljr", "onFailure:" + t);
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}
