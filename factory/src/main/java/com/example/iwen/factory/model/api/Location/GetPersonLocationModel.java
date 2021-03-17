package com.example.iwen.factory.model.api.Location;

/**
 * 获取定位任务的请求model
 *
 * @author : iwen大大怪
 * create : 11-26 026 11:27
 */
public class GetPersonLocationModel {
    private String workId;
    // private String date;

    public GetPersonLocationModel(String workId) {
        this.workId = workId;
        // this.date = date;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
}
