package com.example.iwen.factory.presenter.sign;

import com.example.iwen.common.factory.presenter.BaseContract;
import com.example.iwen.factory.model.db.sign.SignRspModel;

/**
 * 打卡签到的契约
 *
 * @author : iwen大大怪
 * create : 11-29 029 15:02
 */
public interface SignContract {
    interface View extends BaseContract.View<SignContract.Presenter> {
        // 获取成功,返回一个SignRspModel
        void signSuccess(SignRspModel signRspModel);
    }

    interface Presenter extends BaseContract.Presenter {
        /**
         * 发起签到请求
         *
         * @param daySignId 记录id
         * @param collectId
         * @param workId    工号
         * @param info      健康信息
         * @param date      打卡时间 格式“xxx-xx-xx xx:xx”
         * @param icon      拍照打卡照片
         * @param location  经纬度
         */
        void sign(String daySignId, String collectId, String workId, String info, String date, String icon, String location);
    }
}
