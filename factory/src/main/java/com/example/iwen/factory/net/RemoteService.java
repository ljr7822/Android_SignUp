package com.example.iwen.factory.net;

import com.example.iwen.factory.model.api.Location.GetPersonLocationModel;
import com.example.iwen.factory.model.api.RspModel;
import com.example.iwen.factory.model.api.account.AccountRspModel;
import com.example.iwen.factory.model.api.account.LoginModel;
import com.example.iwen.factory.model.api.account.RegisterModel;
import com.example.iwen.factory.model.api.account.UserModel;
import com.example.iwen.factory.model.api.department.DepartmentModel;
import com.example.iwen.factory.model.api.notice.NoticeModel;
import com.example.iwen.factory.model.api.sign.SignModel;
import com.example.iwen.factory.model.api.user.UserUpdateModel;
import com.example.iwen.factory.model.db.account.LoginRspModel;
import com.example.iwen.factory.model.db.account.PersonalRecordRspModel;
import com.example.iwen.factory.model.db.account.UserRspModel;
import com.example.iwen.factory.model.db.department.DepartmentRspModel;
import com.example.iwen.factory.model.db.location.LocationTaskList;
import com.example.iwen.factory.model.db.notice.NoticeRspModel;
import com.example.iwen.factory.model.db.sign.SignRspModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * 网络请求的所有接口
 *
 * @author : Iwen大大怪
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
     * @return 返回的是RspModel<LoginRspModel>
     */
    @POST("user/login")
    Call<RspModel<LoginRspModel>> userLogin(@Body LoginModel model);

    /**
     * 获取打卡任务列表
     *
     * @param getPersonLocationModel 要获取的任务请求参数
     * @return 请求定位列表后返回的数据
     */
    @POST("task/getPerson")
    Call<RspModel<List<LocationTaskList>>> getPerson(@Body GetPersonLocationModel getPersonLocationModel);

    /**
     * 签到打卡
     *
     * @param signModel 签到打卡传入的请求参数
     * @return 请求成功后返回的数据
     */
    @POST("sign")
    Call<RspModel<SignRspModel>> sign(@Body SignModel signModel);

    /**
     * 获取公告列表
     *
     * @param noticeModel request
     * @return <List<NoticeRspModel>>
     */
    @POST("notice/getList")
    Call<RspModel<List<NoticeRspModel>>> getCementList(@Body NoticeModel noticeModel);

    /**
     * 获取部门列表
     *
     * @param departmentModel DepartmentModel
     * @return <List<DepartmentRspModel>>
     */
    @POST("department/ggetlist")
    Call<RspModel<List<DepartmentRspModel>>> getDepartmentList(@Body DepartmentModel departmentModel);

    /**
     * 获取个人信息
     *
     * @param userModel UserModel
     * @return UserRspModel
     */
    @POST("user/gget")
    Call<RspModel<UserRspModel>> getUser(@Body UserModel userModel);

    /**
     * 获取个人打卡记录
     *
     * @param userModel UserModel
     * @return List<PersonalRspRecord>
     */
    @POST("sign/person/record")
    Call<RspModel<List<PersonalRecordRspModel>>> getPersonalRecord(@Body UserModel userModel);

    /**
     * 用户更新的接口
     * @param model UserUpdateModel
     * @return User
     */
    @POST("user/update")
    Call<RspModel<UserRspModel>> userUpdate(@Body UserUpdateModel model);
}
