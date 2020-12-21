package com.example.iwen.singup.helper;

import android.content.Context;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.Trace;
import com.baidu.trace.model.OnTraceListener;
import com.baidu.trace.model.PushMessage;

/**
 * 轨迹服务类
 * 获取用户实时轨迹并上传至服务器
 *
 * @author : iwen大大怪
 * create : 2020-12-21 11:46
 */
public class TraceService {

    /**
     * 轨迹服务方法
     *
     * @param inServiceId  轨迹服务ID
     * @param inEntityName 设备标识
     * @param context      上下文
     */
    public void initTrace(long inServiceId, String inEntityName, Context context) {

        // 轨迹服务ID  225246
        long serviceId = inServiceId;
        // 设备标识
        String entityName = inEntityName;
        // 是否需要对象存储服务，默认为：false，关闭对象存储服务。
        // 注：鹰眼 Android SDK v3.0以上版本支持随轨迹上传图像等对象数据，
        // 若需使用此功能，该参数需设为 true，且需导入bos-android-sdk-1.0.2.jar。
        boolean isNeedObjectStorage = false;
        // 初始化轨迹服务
        final Trace mTrace = new Trace(serviceId, entityName, isNeedObjectStorage);
        // 初始化轨迹服务客户端
        LBSTraceClient mTraceClient = new LBSTraceClient(context);
        // 定位周期(单位:秒)
        int gatherInterval = 5;
        // 打包回传周期(单位:秒)
        int packInterval = 10;
        // 设置定位和打包周期
        mTraceClient.setInterval(gatherInterval, packInterval);
        // 初始化轨迹服务监听器
        final OnTraceListener mTraceListener = new OnTraceListener() {
            @Override
            public void onBindServiceCallback(int i, String s) {

            }

            // 开启服务回调接口
            @Override
            public void onStartTraceCallback(int status, String message) {
            }

            // 停止服务回调
            @Override
            public void onStopTraceCallback(int status, String message) {
            }

            // 开启采集回调
            @Override
            public void onStartGatherCallback(int status, String message) {
            }

            // 停止采集回调
            @Override
            public void onStopGatherCallback(int status, String message) {
            }

            // 推送回调
            @Override
            public void onPushCallback(byte messageNo, PushMessage message) {
            }

            @Override
            public void onInitBOSCallback(int i, String s) {

            }
        };
    }
}
