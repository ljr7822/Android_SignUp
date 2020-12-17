package com.example.iwen.factory.model.db;

/**
 * 用户信息
 * @author : Iwen大大怪
 * create : 2020/11/17 16:45
 */
public class User {

    private String name; // 姓名
    private String workId; // 工号
    private String departmentName; // 部门名称
    private String departmentId; // 部门id
    private String icon; // 头像
    private String phoneNumber; // 手机号
    private int sex = 0;

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
}
