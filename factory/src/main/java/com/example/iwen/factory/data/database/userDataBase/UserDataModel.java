package com.example.iwen.factory.data.database.userDataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 用户数据的实体
 *
 * @author iwen大大怪
 * Create to 2021/03/18 20:17
 */
@Entity
public class UserDataModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String workId;
    @ColumnInfo
    private String departmentName;
    @ColumnInfo
    private String departmentId;
    @ColumnInfo
    private String icon;
    @ColumnInfo
    private String phoneNumber;
    @ColumnInfo
    private String roleName;
    @ColumnInfo
    private boolean messageFinish;
    @ColumnInfo
    private boolean oldPassword;

    public UserDataModel(String name, String workId, String departmentName, String departmentId, String icon, String phoneNumber, String roleName, boolean messageFinish, boolean oldPassword) {
        this.name = name;
        this.workId = workId;
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.icon = icon;
        this.phoneNumber = phoneNumber;
        this.roleName = roleName;
        this.messageFinish = messageFinish;
        this.oldPassword = oldPassword;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isMessageFinish() {
        return messageFinish;
    }

    public void setMessageFinish(boolean messageFinish) {
        this.messageFinish = messageFinish;
    }

    public boolean isOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(boolean oldPassword) {
        this.oldPassword = oldPassword;
    }
}
