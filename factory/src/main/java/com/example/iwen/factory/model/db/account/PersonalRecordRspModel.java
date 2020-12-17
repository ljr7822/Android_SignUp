package com.example.iwen.factory.model.db.account;

/**
 * 个人打卡记录
 *
 * @author : iwen大大怪
 * create : 12-10 010 21:41
 */
public class PersonalRecordRspModel {
    /**
     * signInId   String  // 记录id
     * collectId   String  // 信息收集id
     * collectName  String // 信息收集名称 普通收集 日常打卡.....
     * work    String //  员工信息（name(id)）
     * date    String // 日期 格式（xxxx-xx-xx）
     * time    String // 时间  格式(xx:xx)
     * departmentId  String // 部门id
     * ifSigIn  String // 是否签到
     * ifLate   String // 是否迟到
     * locationId  String // 定位id
     * info   String // 具体地址
     * icon   String // 拍照照片
     * locationName   String // 定位名称
     * department  String // 部门
     */

    private String signInId;
    private String collectId;
    private String collectName;
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

    public PersonalRecordRspModel(String signInId, String collectId, String collectName, String work, String date, String time, String departmentId, String ifSigIn, String ifLate, String locationId, String info, String icon, String locationName, String department) {
        this.signInId = signInId;
        this.collectId = collectId;
        this.collectName = collectName;
        this.work = work;
        this.date = date;
        this.time = time;
        this.departmentId = departmentId;
        this.ifSigIn = ifSigIn;
        this.ifLate = ifLate;
        this.locationId = locationId;
        this.info = info;
        this.icon = icon;
        this.locationName = locationName;
        this.department = department;
    }

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

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
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
