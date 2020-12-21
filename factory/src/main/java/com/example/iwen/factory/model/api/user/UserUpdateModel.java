package com.example.iwen.factory.model.api.user;

/**
 * 用户更新信息所使用的Model
 */
public class UserUpdateModel {
    /**
     * workId   String//必传
     * name String // 姓名
     * phoneNumber   String // 手机号
     * avatar   String // 头像url 可空
     * department  String  // 部门
     * password  String  // 密码
     * mac   String  // 设备唯一识别码
     * sex int // 性别
     */
    private String workId;
    private String name;
    private String phoneNumber;
    private String avatar;
    private String department;
    private String password;
    private String mac;
    private int sex;

    public UserUpdateModel(String workId, String name, String phoneNumber, String avatar, String department, String password, String mac, int sex) {
        this.workId = workId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.department = department;
        this.password = password;
        this.mac = mac;
        this.sex = sex;
    }

    public UserUpdateModel(String workId, String name, String phoneNumber, String department, String password, String mac, int sex) {
        this.workId = workId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.password = password;
        this.mac = mac;
        this.sex = sex;
    }

    public UserUpdateModel(String workId, String name, String phoneNumber, String department, String password, int sex) {
        this.workId = workId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.password = password;
        this.sex = sex;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
