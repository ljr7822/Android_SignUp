package com.example.iwen.factory.model.api.notice;

/**
 * 发起获取公告列表所携带的数据模型
 *
 * @author : iwen大大怪
 * create : 12-1 001 17:58
 */
public class NoticeModel {
    private String departmentId;

    public NoticeModel() {
    }

    public NoticeModel(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
