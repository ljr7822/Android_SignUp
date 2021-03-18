package com.example.iwen.factory.net;

import com.example.iwen.common.Common;
import com.example.iwen.factory.Factory;
import com.example.iwen.factory.okhttp.OkHttpSSH;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 *
 * @author : Iwen大大怪
 * create : 2020/11/17 19:22
 */
public class Network {

    /**
     * 构建一个Retrofit
     */
    public static Retrofit getRetrofit() {
        // 得到一个OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                // 跳过ssh检查
                .sslSocketFactory(OkHttpSSH.createSSLSocketFactory(), new OkHttpSSH.TrustAllCerts())
                .build();
        Retrofit.Builder builder = new Retrofit.Builder();

        // 设置连接电脑
        // 获取Retrofit对象
        Retrofit retrofit = builder.baseUrl(Common.Constance.API_URL)
                .client(client) // 设置client
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器
                .build();
        return retrofit;
    }
}
