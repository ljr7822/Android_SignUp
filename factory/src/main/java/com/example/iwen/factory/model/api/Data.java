package com.example.iwen.factory.model.api;

import java.util.Date;

/**
 * 接收服务器返回数据的类
 * author : Iwen大大怪
 * create : 11-18 018 8:50
 */
public class Data<T> {
    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
