package com.example.iwen.factory.model.api.sign;

/**
 * 进入签到打卡界面需要获取签到的收集信息
 *
 * @author : iwen大大怪
 * create : 2020-12-22 13:33
 */
public class InSignUpModel {
    /**
     * collectId 记录id
     */
    private String id;

    public InSignUpModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
