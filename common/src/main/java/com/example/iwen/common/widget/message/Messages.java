package com.example.iwen.common.widget.message;

/**
 * author : Iwen大大怪
 * create : 11-23 023 14:04
 */
public class Messages {

    private String title;
    private String desc;
    private String date;
    private String time;
    private String isSignTag;
    private long remindTime,remindTimeNoDay;
    private int id,isAlerted,isRepeat,imgId;

    public Messages(String title, String desc, String date, String time, String isSignTag,int id, int imgId) {
        this.title = title;
        this.desc = desc;
        this.date = date; // 日期
        this.time = time; // 时间
        this.isSignTag = isSignTag;
        this.id = id;
        this.imgId = imgId; // 图片id
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public String getIsSignTag() {
        return isSignTag;
    }

    public void setIsSignTag(String isSignTag) {
        this.isSignTag = isSignTag;
    }

    public long getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(long remindTime) {
        this.remindTime = remindTime;
    }

    public long getRemindTimeNoDay() {
        return remindTimeNoDay;
    }

    public void setRemindTimeNoDay(long remindTimeNoDay) {
        this.remindTimeNoDay = remindTimeNoDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsAlerted() {
        return isAlerted;
    }

    public void setIsAlerted(int isAlerted) {
        this.isAlerted = isAlerted;
    }

    public int getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(int isRepeat) {
        this.isRepeat = isRepeat;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
