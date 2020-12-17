package com.example.iwen.factory.model.db.notice;

/**
 * @author : iwen大大怪
 * create : 12-1 001 17:55
 */
public class NoticeRspModel {
    /**
     * noticeId   String // 公告id
     * departmentName   String // 公告发布部门名称
     * content    String//公告内容
     * departmentId  / String//公告发布部门id
     * date    String//发布日期（非自主填写，前端根据点击发送时，自动获取）
     * type    String//公告类型
     * title   String // 公告标题
     */

    private String noticeId;
    private String departmentName;
    private String content;
    private String departmentId;
    private String date;
    private String type;
    private String title;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
