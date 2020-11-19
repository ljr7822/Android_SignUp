package com.example.iwen.factory.model.api.account;

import com.example.iwen.factory.model.db.User;

/**
 * 接收到的数据实体类
 * author : Iwen大大怪
 * create : 11-18 018 8:53
 */
public class UserRspModel {
    // 用户基本信息
    private User user;
    private String name;
    private String workId;
    private String departmentName;
    private String departmentId;
    private String icon;
    private String phoneNumber;
    private String roleName;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRspModel{" +
                "name='" + name + '\'' +
                ", workId='" + workId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", icon='" + icon + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
