package com.example.iwen.factory.model.db;

/**
 * 注册返回数据模型
 *
 * @author : iwen大大怪
 * create : 11-26 026 9:50
 */
public class RegisterRspModel {
    /*
            {
        "code":0,
        "msg":"添加成功",
        "data":{
            "workId":workId,
            "department":departmentName,
            "password":defualtPassword,//  后台自动给出比如6个8
            "role":roleName
            "name":name
        }
        }
     */

    private String workId;
    private String department;
    private String password;
    private String role;
    private String name;

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
