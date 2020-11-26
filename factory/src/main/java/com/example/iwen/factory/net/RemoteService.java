package com.example.iwen.factory.net;

import com.example.iwen.factory.model.api.Location.GetPersonLocationModel;
import com.example.iwen.factory.model.api.Location.LocationTaskList;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.api.account.AccountRspModel;
import com.example.iwen.factory.model.api.account.RegisterModel;
import com.example.iwen.factory.model.api.account.LoginModel;
import com.example.iwen.factory.model.db.RspListModel;
import com.example.iwen.factory.model.db.LoginRspModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 网络请求的所有接口
 * author : Iwen大大怪
 * create : 2020/11/17 22:45
 */
public interface RemoteService {
    /**
     * 注册的网络请求接口
     *
     * @param model 传入的是RegisterModel
     * @return 返回的是RspModel<AccountRspModel>
     */
    @POST("account/register")
    Call<RspModel<AccountRspModel>> accountRegister(@Body RegisterModel model);


    /**
     * 登录的网络请求接口
     *
     * @param model 传入的是LoginModel
     * @return 返回的是Data<UserInfo>
     */
    @POST("user/login")
    Call<RspModel<LoginRspModel>> userLogin(@Body LoginModel model);


    /**
     * 获取单个打卡任务
     * @param getPersonLocationModel 要获取的任务请求参数
     * @return 请求定位列表后返回的数据
     */
    @POST("task/getPerson")
    Call<RspModel<RspListModel<LocationTaskList>>> getPerson(@Body GetPersonLocationModel getPersonLocationModel);
}
