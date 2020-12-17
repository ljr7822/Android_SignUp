package com.example.iwen.factory.model.api.account;

/**
 * 发起用户请求的model
 *
 * @author : iwen大大怪
 * create : 12-10 010 18:49
 */
public class UserModel {
    private String workId;
    private String avatar;

    public UserModel(String workId) {
        this.workId = workId;
    }

    public UserModel(String avatar, String workId) {
        this.avatar = avatar;
        this.workId = workId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
