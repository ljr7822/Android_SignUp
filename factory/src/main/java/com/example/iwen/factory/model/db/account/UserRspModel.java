package com.example.iwen.factory.model.db.account;

/**
 * 用户登录返回
 * 接口请求后接收到的数据实体类
 *
 * @author : Iwen大大怪
 * create : 11-18 018 8:53
 */
public class UserRspModel {
    /**
     * name   String
     * workId   String
     * departmentName   String
     * departmentId     String
     * icon     String
     * phoneNumber    String
     */
    private String name; // 姓名
    private String workId; // 工号
    private String departmentName; // 部门名称
    private String departmentId; // 部门id
    private String icon; // 头像url
    private String phoneNumber; // 电话号码

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

    @Override
    public String toString() {
        return "UserRspModel{" +
                "name='" + name + '\'' +
                ", workId='" + workId + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", icon='" + icon + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
