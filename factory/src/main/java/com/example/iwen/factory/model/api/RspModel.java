package com.example.iwen.factory.model.api;

import java.util.Date;

/**
 * author : Iwen大大怪
 * create : 2020/11/17 12:05
 */@SuppressWarnings({"WeakerAccess", "unused"})
public class RspModel<T> {

    private int code;
    private String message;
    private T result;

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}