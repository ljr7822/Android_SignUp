package com.example.iwen.factory;

import com.example.iwen.common.app.Application;

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
    // 初始化线程池
    private final Executor mExecutor;

    static {
        instance = new Factory();
    }

    private Factory() {
        // 新建一个4线程的线程池
        mExecutor = Executors.newFixedThreadPool(4);
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
     * 异步允许方法
     *
     * @param runnable Runnable
     */
    public static void runOnAsync(Runnable runnable) {
        // 拿到单例，拿到线程池，然后异步执行
        instance.mExecutor.execute(runnable);
    }
}

