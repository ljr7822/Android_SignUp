package com.example.iwen.factory.model.db.location;

/**
 * 获取定位信息返回的数据模型
 *
 * @author : iwen大大怪
 * create : 11-26 026 11:49
 */
public class LocationTaskList {
    /**
     * "signInId": "14486315",
     * "collectId": "6575",
     * "work": "陈晨(10010002)",
     * "date": "2020-11-26",
     * "time": null,
     * "departmentId": "1001",
     * "ifSign": null,
     * "ifLate": null,
     * "locationId": "6361",
     * "info": null,
     * "icon": null,
     * "locationName": "重庆市荣昌区学院路160号",
     * "departmentName": "销售部",
     * "collectName": "1",
     * "collectType": "拍照打卡"，"普通签到"
     */
    private String signInId;
    private String collectId;
    private String work;
    private String date;
    private String time;
    private String departmentId;
    private String ifSign;
    private String ifLate;
    private String locationId;
    private String info;
    private String icon;
    private String locationName;
    private String departmentName;
    private String collectType;

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

    public String getIfSign() {
        return ifSign;
    }

    public void setIfSignIn(String ifSign) {
        this.ifSign = ifSign;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }
}
