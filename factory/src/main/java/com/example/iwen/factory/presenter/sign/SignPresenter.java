package com.example.iwen.factory.presenter.sign;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.factory.data.helper.SignHelper;
import com.example.iwen.factory.model.api.sign.SignModel;
import com.example.iwen.factory.model.db.sign.SignRspModel;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * 签到p层
 *
 * @author : iwen大大怪
 * create : 11-29 029 15:18
 */
public class SignPresenter
        extends BasePresenter<SignContract.View>
        implements SignContract.Presenter, DataSource.Callback<SignRspModel> {
    public SignPresenter(SignContract.View view) {
        super(view);
    }

    @Override
    public void sign(String daySignId, String collectId, String workId, String info, String date, String icon, String location) {
        start();
        SignContract.View view = getView();
        // 构造发起请求的数据模型
        SignModel signModel = new SignModel(daySignId, collectId, workId, info, date, icon, location);
        // 发起网络请求
        SignHelper.sign(signModel, this);
    }

    // 成功网络回调
    @Override
    public void onDataLoaded(final SignRspModel signRspModel) {
        // 得到View接口
        final SignContract.View view = getView();
        if (view != null) {
            // 此时是从网络回送回来的，并不保证处于主线程状态
            // 进行线程切换
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    // 调用成功
                    view.signSuccess(signRspModel);
                }
            });
        }
    }

    // 失败网络回调
    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知网络失败
        // 得到View接口
        final SignContract.View view = getView();
        if (view == null)
            return;
        // 此时是从网络回送回来的，并不保证处于主线程状态
        // 进行线程切换
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 网络失败
                view.showError(strRes);
            }
        });
    }
}
