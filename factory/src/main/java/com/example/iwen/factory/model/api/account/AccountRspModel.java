package com.example.iwen.factory.model.api.account;

import com.example.iwen.factory.model.db.User;

/**
 * author : Iwen大大怪
 * create : 2020/11/17 22:46
 */
public class AccountRspModel {
    // 用户基本信息
    private User user;
    // 当前登录的账号
    private String account;
    // 当前登录成功后获取的Token
    // 可以通过Token获取用户的所有信息
    private String token;
    // 标识是否已经绑定了设备的pushId
    private boolean isBind;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }

    @Override
    public String toString() {
        return "AccountRspModel{" +
                "mUser=" + user +
                ", account='" + account + '\'' +
                ", token='" + token + '\'' +
                ", isBind=" + isBind +
                '}';
    }
}
