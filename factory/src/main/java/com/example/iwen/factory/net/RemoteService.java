package com.example.iwen.factory.net;

import com.example.iwen.factory.model.api.Data;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.api.account.AccountRspModel;
import com.example.iwen.factory.model.api.account.RegisterModel;
import com.example.iwen.factory.model.api.account.LoginModel;
import com.example.iwen.factory.model.api.account.UserRspModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    @POST("login")
    Call<RspModel<UserRspModel>> userLogin(@Body LoginModel model);
}
