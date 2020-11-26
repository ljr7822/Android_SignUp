package com.example.iwen.factory.model.db;

import java.util.List;

/**
 * 请求定位回调的数据模型
 *
 * @author : iwen大大怪
 * create : 11-26 026 11:33
 */
public class RspListModel<T> {
    /**
     * {
     * "code":0,
     * "msg":"请求成功！",
     * "data":task
     * }
     */

    private List<T> mList;

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> list) {
        mList = list;
    }
}
