package com.example.iwen.factory.data.helper;

import android.util.Log;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.Location.GetPersonLocationModel;
import com.example.iwen.factory.model.api.Location.LocationTaskList;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.db.RspListModel;
import com.example.iwen.factory.net.Network;
import com.example.iwen.factory.net.RemoteService;
import com.example.iwen.factory.presenter.location.LocationPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 定位相关的具体操作
 * @author : iwen大大怪
 * create : 11-26 026 11:59
 */
public class LocationHelper {

    /**
     * 获取任务列表的接口，异步调用
     *  @param getPersonLocationModel    传递一个注册的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void getPerson(GetPersonLocationModel getPersonLocationModel, final LocationPresenter callback) {
        // TODO 获取定位任务列表
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 创建call
        Call<RspModel<RspListModel<LocationTaskList>>> call = service.getPerson(getPersonLocationModel);
        // 发起请求
        call.enqueue(new Callback<RspModel<RspListModel<LocationTaskList>>>() {
            @Override
            public void onResponse(Call<RspModel<RspListModel<LocationTaskList>>> call, Response<RspModel<RspListModel<LocationTaskList>>> response) {
                // 先取出第一层数据
                RspModel<RspListModel<LocationTaskList>> rspModel = response.body();
                if (rspModel.success()){
                    // 判断状态码，再取出第二层数据
                    RspListModel<LocationTaskList> rspListModel = rspModel.getData();
                    Log.e("ljr","第二层"+rspModel.getData());
                    // 回调一个用户数据
                    callback.onDataLoaded(rspListModel);
                }else {
                    // TODO 对返回的RspModel中的失败的code进行解析，解析到对应的String资源中
                    // callback.onDataNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<RspModel<RspListModel<LocationTaskList>>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
                Log.e("ljr","onFailure"+t);
            }
        });
    }
}
