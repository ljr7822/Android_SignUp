package com.example.iwen.factory;

import androidx.annotation.StringRes;

import com.example.iwen.common.app.Application;
import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.model.api.RspModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 网络处理
 * author : Iwen大大怪
 * create : 2020/11/16 9:56
 */
public class Factory {
    // 单例模式
    private static final Factory instance;
    // 初始化全局的线程池
    private final Executor mExecutor;
    // 维持一个全局的GSON
    private final Gson mGson;

    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个4线程的线程池
        mExecutor = Executors.newFixedThreadPool(4);
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS") // 设置时间格式
                //.setExclusionStrategies() // TODO 设置过滤器,数据库级别的Model不进行json转换
                .create();
    }

    /**
     * 返回全局的Application
     *
     * @return Application
     */
    public static Application app() {
        return Application.getInstance();
    }

    /**
     * 异步运行方法
     *
     * @param runnable Runnable
     */
    public static void runOnAsync(Runnable runnable) {
        // 拿到单例，拿到线程池，然后异步执行
        instance.mExecutor.execute(runnable);
    }

    /**
     * 返回一个全局的Gson，在这里可以进行Gson的一些全局的初始化
     *
     * @return gson
     */
    public static Gson getGson() {
        return instance.mGson;
    }

//    /**
//     * 进行错误数据的解析，把网络返回的code值进行统一的规划，并返回为一个String资源
//     *
//     * @param model RspModel
//     * @param callback DataSource.FailedCallback 用于返回一个错误的资源id
//     */
//    public static void decodeRspCode(RspModel model, DataSource.FailedCallback callback) {
//        if (model==null)
//            return;
//        // 进行code区分
//        switch (model.getCode()) {
//            case RspModel.SUCCEED:
//                return;
//            case RspModel.ERROR_SERVICE:
//                decodeRspCode(R.string.data_rsp_error_service, callback);
//                break;
//            case RspModel.ERROR_NOT_FOUND_USER:
//                decodeRspCode(R.string.data_rsp_error_not_found_user, callback);
//                break;
//            case RspModel.ERROR_NOT_FOUND_GROUP:
//                decodeRspCode(R.string.data_rsp_error_not_found_group, callback);
//                break;
//            case RspModel.ERROR_NOT_FOUND_GROUP_MEMBER:
//                decodeRspCode(R.string.data_rsp_error_not_found_group_member, callback);
//                break;
//            case RspModel.ERROR_CREATE_USER:
//                decodeRspCode(R.string.data_rsp_error_create_user, callback);
//                break;
//            case RspModel.ERROR_CREATE_GROUP:
//                decodeRspCode(R.string.data_rsp_error_create_group, callback);
//                break;
//            case RspModel.ERROR_CREATE_MESSAGE:
//                decodeRspCode(R.string.data_rsp_error_create_message, callback);
//                break;
//            case RspModel.ERROR_PARAMETERS:
//                decodeRspCode(R.string.data_rsp_error_parameters, callback);
//                break;
//            case RspModel.ERROR_PARAMETERS_EXIST_ACCOUNT:
//                decodeRspCode(R.string.data_rsp_error_parameters_exist_account, callback);
//                break;
//            case RspModel.ERROR_PARAMETERS_EXIST_NAME:
//                decodeRspCode(R.string.data_rsp_error_parameters_exist_name, callback);
//                break;
//            case RspModel.ERROR_ACCOUNT_TOKEN:
//                Application.showToast(R.string.data_rsp_error_account_token);
//                instance.logout();
//                break;
//            case RspModel.ERROR_ACCOUNT_LOGIN:
//                decodeRspCode(R.string.data_rsp_error_account_login, callback);
//                break;
//            case RspModel.ERROR_ACCOUNT_REGISTER:
//                decodeRspCode(R.string.data_rsp_error_account_register, callback);
//                break;
//            case RspModel.ERROR_ACCOUNT_NO_PERMISSION:
//                decodeRspCode(R.string.data_rsp_error_account_no_permission, callback);
//                break;
//            case RspModel.ERROR_UNKNOWN:
//            default:
//                decodeRspCode(R.string.data_rsp_error_unknown, callback);
//                break;
//        }
//    }

    private static void decodeRspCode(@StringRes final int resId, final DataSource.FailedCallback callback){
        if (callback != null)
            callback.onDataNotAvailable(resId);
    }

    /**
     * 收到账户退出的消息需要进行账户退出重新登录
     */
    private void logout() {

    }
}

