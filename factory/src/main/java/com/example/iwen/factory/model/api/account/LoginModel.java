package com.example.iwen.factory.model.api.account;

/**
 * 登录使用的请求model
 *
 * @author : Iwen大大怪
 * create : 11-18 018 8:55
 */
public class LoginModel {

    private String workId; // 工号
    private String password; // 密码
    private String deviceId; // 手机序列码

    public LoginModel(String workId, String password) {
        this.workId = workId;
        this.password = password;
    }

    public LoginModel(String workId, String password, String deviceId) {
        this.workId = workId;
        this.password = password;
        this.deviceId = deviceId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
