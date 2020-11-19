package com.example.iwen.factory.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Okhttp使用
 * author : Iwen大大怪
 * create : 11-18 018 22:32
 */
public class OkhttpUse {
    private void okhttp(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().url("").build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content = response.body().string();
                System.out.println("请求成功"+content);
            }
        });
    }
}
