package com.example.iwen.factory.model.api.user;

/**
 * 用户更新头像的请求model
 *
 * @author iwen大大怪
 * Create to 2021/03/19 1:10
 */
public class UserUpdateIconModel {
    private String workId;
    private String icon;

    public UserUpdateIconModel(String workId, String icon) {
        this.workId = workId;
        this.icon = icon;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
