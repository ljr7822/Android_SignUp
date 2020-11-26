package com.example.iwen.factory.model.api.Location;

import com.example.iwen.common.utils.DateTimeUtil;

import java.util.Date;

/**
 * 获取定位任务的请求model
 *
 * @author : iwen大大怪
 * create : 11-26 026 11:27
 */
public class GetPersonLocationModel {
    private String workId;
    private String mDate;

    public GetPersonLocationModel(String workId, String date) {
        this.workId = workId;
        mDate = date;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
