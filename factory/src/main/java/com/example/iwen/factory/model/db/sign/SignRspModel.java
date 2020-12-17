package com.example.iwen.factory.model.db.sign;

/**
 * 签到返回的数据模型
 *
 * @author : iwen大大怪
 * create : 11-29 029 15:12
 */
public class SignRspModel {
    /**
     * signInId   String  // 记录id
     * collectId   String  // 信息收集id
     * collectName  String
     * work    String //  员工信息（name(id)）
     * date    String // 日期 格式（xxxx-xx-xx）
     * time    String // 时间  格式(xx:xx)
     * departmentId  String
     * ifSigIn  String
     * ifLate   String
     * locationId  String
     * info   String
     * icon   String
     * locationName   String
     * department  String
     */
    private String signInId;
    private String collectId;
    private String work;
    private String date;
    private String time;
    private String departmentId;
    private String ifSigIn;
    private String ifLate;
    private String locationId;
    private String info;
    private String icon;
    private String locationName;
    private String department;

    public String getSignInId() {
        return signInId;
    }

    public void setSignInId(String signInId) {
        this.signInId = signInId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getIfSigIn() {
        return ifSigIn;
    }

    public void setIfSigIn(String ifSigIn) {
        this.ifSigIn = ifSigIn;
    }

    public String getIfLate() {
        return ifLate;
    }

    public void setIfLate(String ifLate) {
        this.ifLate = ifLate;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
