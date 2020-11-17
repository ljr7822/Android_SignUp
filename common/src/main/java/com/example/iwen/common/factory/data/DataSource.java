package com.example.iwen.common.factory.data;

import androidx.annotation.StringRes;

/**
 * 数据源接口定义
 * author : Iwen大大怪
 * create : 2020/11/17 18:32
 */
public interface DataSource {

    /**
     * 同时包括了成功与失败的回调接口
     * @param <T> 泛型
     */
    interface Callback<T> extends  SucceedCallback<T>,FailedCallback{

    }
    /**
     * 只关注成功的接口
     * @param <T> 泛型
     */
    interface SucceedCallback<T>{
        // 数据加载成功，网络请求成功的回调
        void onDataLoaded(T t);
    }

    /**
     * 只关注失败的接口
     */
    interface FailedCallback{
        // 数据加载失败，网络请求成功的回调失败的回调
        void onDataNotAvailable(@StringRes int strRes);
    }
}
