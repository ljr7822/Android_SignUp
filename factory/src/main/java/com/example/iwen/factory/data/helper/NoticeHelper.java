package com.example.iwen.factory.data.helper;

import android.util.Log;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.api.notice.NoticeModel;
import com.example.iwen.factory.model.db.notice.NoticeRspModel;
import com.example.iwen.factory.net.Network;
import com.example.iwen.factory.net.RemoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 获取公告列表的相关网络请求
 *
 * @author : iwen大大怪
 * create : 12-1 001 18:29
 */
public class NoticeHelper {

    /**
     * 获取公告列表
     * @param model 请求的model
     * @param callback 回调
     */
    public static void getNoticeList(NoticeModel model,final DataSource.Callback<List<NoticeRspModel>> callback){
        // 获得Retrofit
        RemoteService service = Network.getRetrofit().create(RemoteService.class);
        // 创建call
        Call<RspModel<List<NoticeRspModel>>> call = service.getCementList(model);
        call.enqueue(new Callback<RspModel<List<NoticeRspModel>>>() {
            @Override
            public void onResponse(Call<RspModel<List<NoticeRspModel>>> call, Response<RspModel<List<NoticeRspModel>>> response) {
                RspModel<List<NoticeRspModel>> rspModel = response.body();
                if (rspModel.success()) {
                    List<NoticeRspModel> noticeRspModels = rspModel.getData();
                    callback.onDataLoaded(noticeRspModels);
                }
            }

            @Override
            public void onFailure(Call<RspModel<List<NoticeRspModel>>> call, Throwable t) {
                Log.e("ljr", "onFailure:" + t);
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
    }
}
