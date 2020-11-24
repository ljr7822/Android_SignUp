package com.example.iwen.common.widget.cementitem;

/**
 * 公告数据类
 *
 * @author : Iwen大大怪
 * create : 11-23 023 15:45
 */
public class Cements {
    private String title;
    private String desc;
    private String time;
    private int imgId;

    public Cements(String title, String desc, String time, int imgId) {
        this.title = title;
        this.desc = desc;
        this.time = time;
        this.imgId = imgId;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
