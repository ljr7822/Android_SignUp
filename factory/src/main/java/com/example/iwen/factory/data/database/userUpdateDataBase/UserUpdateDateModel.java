package com.example.iwen.factory.data.database.userUpdateDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 用户更新的实体
 *
 * @author iwen大大怪
 * Create to 2021/03/18 23:59
 */
@Entity
public class UserUpdateDateModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name; // 姓名
    @ColumnInfo
    private String workId; // 工号
    @ColumnInfo
    private String departmentName; // 部门名称
    @ColumnInfo
    private String departmentId; // 部门id
    @ColumnInfo
    private String icon; // 头像url
    @ColumnInfo
    private String phoneNumber; // 电话号码

    public UserUpdateDateModel(String name, String workId, String departmentName, String icon, String phoneNumber) {
        this.name = name;
        this.workId = workId;
        this.departmentName = departmentName;
        this.icon = icon;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
