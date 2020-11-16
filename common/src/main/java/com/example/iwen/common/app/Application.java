package com.example.iwen.common.app;

import android.widget.Toast;

import androidx.annotation.StringRes;

import net.qiujuer.genius.kit.handler.Run;

import java.io.File;

/**
 * author : Iwen大大怪
 * create : 2020/11/15 9:22
 */
public class Application extends android.app.Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 外部获取单例
     *
     * @return Application
     */
    public static Application getInstance() {
        return instance;
    }

    /**
     * 获取缓存文件夹地址
     * @return 当前App的缓存文件夹地址
     */
    public static File getCacheDirFile(){
        return instance.getCacheDir();
    }

    /**
     * 获取头像临时缓存地址
     *
     * @return 头像临时缓存地址
     */
    public static File getAvatarTempFile() {
        //得到头像目录的缓存地址
        File dir = new File(getCacheDirFile(), "avatar");
        //创建所有的对应的文件夹
        dir.mkdirs();
        //删除旧的缓存文件
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                file.delete();
            }
        }
        //返回一个当前时间戳的目录文件地址
        File path = new File(dir, System.currentTimeMillis() + "jpg");
        return path.getAbsoluteFile();
    }

    /**
     * 显示一个Toast
     *
     * @param msg 字符串
     */
    public static void showToast(final String msg) {
        // Toast 只能在主线程中显示，所有需要进行线程转换，
        // 保证一定是在主线程进行的show操作
        Run.onUiAsync(() -> {
            // 这里进行回调的时候一定就是主线程状态了
            Toast.makeText(instance, msg, Toast.LENGTH_SHORT).show();
        });

    }

    /**
     * 显示一个Toast
     *
     * @param msgId 传递的是字符串的资源
     */
    public static void showToast(@StringRes int msgId) {
        showToast(instance.getString(msgId));
    }
}

