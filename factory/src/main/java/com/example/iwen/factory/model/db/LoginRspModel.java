package com.example.iwen.factory.model.db;

/**
 * 登录返回的数据模型
 *
 * @author : iwen大大怪
 * create : 11-26 026 9:52
 */
public class LoginRspModel {
    /**
     * {
     *     "code": 0,
     *     "msg":"登录成功",
     *     "data": {
     *         "name":"iwen",
     * 		"workId ":"666666",
     * 		"departmentName":"技术部",
     * 		"departmentId":"0001",
     * 		"icon":"icon",
     * 		"phoneNumber":"18289816889",
     * 		"roleName":"角色"
     *     }
     * }
     */
    private String name;
    private String workId;
    private String departmentName;
    private String departmentId;
    private String icon;
    private String phoneNumber;
    private String roleName;

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
}
