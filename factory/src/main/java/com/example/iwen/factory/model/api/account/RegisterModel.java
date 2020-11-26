package com.example.iwen.factory.model.api.account;

/**
 * 注册使用的请求model
 *
 * @author : Iwen大大怪
 * create : 2020/11/17 11:16
 */
public class RegisterModel {
    /*
        departmentId    String
        name    String
        roleId    String
        password  String  //前端将6个8通过md5加密后 将加密后的密码发过去{}
     */
    private String departmentId;
    private String name;
    private String roleId;
    private String password;

    public RegisterModel(String departmentId, String name, String roleId, String password) {
        this.departmentId = departmentId;
        this.name = name;
        this.roleId = roleId;
        this.password = password;
    }

    public RegisterModel(String phone, String password, String name) {
    }


    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
