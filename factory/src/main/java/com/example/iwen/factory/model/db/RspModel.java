package com.example.iwen.factory.model.db;

/**
 * @author : iwen大大怪
 * create : 11-26 026 12:39
 */
public class RspModel<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
