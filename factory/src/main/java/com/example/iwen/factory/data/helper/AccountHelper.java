package com.example.iwen.factory.data.helper;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.factory.R;
import com.example.iwen.factory.model.api.account.RegisterModel;
import com.example.iwen.factory.model.db.User;

/**
 * author : Iwen大大怪
 * create : 2020/11/17 11:13
 */
public class AccountHelper {

    /**
     * 注册的接口，异步调用
     * @param model 传递一个注册的Model进来
     * @param callback 成功与失败的接口回送
     */
    public static void register(RegisterModel model, final DataSource.Callback<User> callback){
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                callback.onDataNotAvailable(R.string.data_rsp_error_parameters);
            }
        }.start();
    }
}
