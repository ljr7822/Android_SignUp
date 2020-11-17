package com.example.iwen.factory.model.db;

import java.util.Date;

/**
 * 保存在手机缓存中的用户信息
 * author : Iwen大大怪
 * create : 2020/11/17 16:45
 */
public class User {
    /**
     *  "workId":workId,
     * 	"department":departmentName,
     * 	"password":defualtPassword,//  后台自动给出比如6个8
     * 	"role":roleName
     * 	"name":name
     */
    private String id;
    private String name;
    private String phone;
    private String portrait;
    private String desc;
    private int sex = 0;
    private String alias; // 我对某人的备注
    private int follows; // 用户关注人的数量
    private int following; // 用户粉丝数量
    private boolean isFollow; // 我与当前User的关系状态，是否已经关注了这个人
    private Date modifyAt; // 时间字段

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public Date getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(Date modifyAt) {
        this.modifyAt = modifyAt;
    }
}
