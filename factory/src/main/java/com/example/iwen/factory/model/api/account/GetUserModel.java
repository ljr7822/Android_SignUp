package com.example.iwen.factory.model.api.account;

/**
 * 获取用户信息的请求模型
 *
 * @author : iwen大大怪
 * create : 11-26 026 9:59
 */
public class GetUserModel {
    /**
     * workId  String //工号
     */
    private String workId;

    public GetUserModel(String workId) {
        this.workId = workId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }
}
