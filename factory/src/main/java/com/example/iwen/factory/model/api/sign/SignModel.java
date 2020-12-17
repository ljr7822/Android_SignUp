package com.example.iwen.factory.model.api.sign;

/**
 * 发起打卡请求时传入的model
 *
 * @author : iwen大大怪
 * create : 11-29 029 15:31
 */
public class SignModel {
    /**
     * daySignId   String// 记录id
     * collectId   String
     * workId     String  //工号
     * info     String  //健康信息
     * date       String    //打卡时间 格式“xxx-xx-xx xx:xx”
     * icon    String// 拍照打卡照片
     * location String // 格式：经度,纬度;
     */
    private String daySignId;
    private String collectId;
    private String workId;
    private String info;
    private String date;
    private String icon;
    private String location;

    public SignModel(String daySignId, String collectId, String workId, String info, String date) {
        this.daySignId = daySignId;
        this.collectId = collectId;
        this.workId = workId;
        this.info = info;
        this.date = date;
    }

    public SignModel(String daySignId, String collectId, String workId, String info, String date, String icon, String location) {
        this.daySignId = daySignId;
        this.collectId = collectId;
        this.workId = workId;
        this.info = info;
        this.date = date;
        this.icon = icon;
        this.location = location;
    }

    public String getDaySignId() {
        return daySignId;
    }

    public void setDaySignId(String daySignId) {
        this.daySignId = daySignId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
