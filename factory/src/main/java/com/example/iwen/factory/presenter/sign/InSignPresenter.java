package com.example.iwen.factory.presenter.sign;

import com.example.iwen.common.factory.data.DataSource;
import com.example.iwen.common.factory.presenter.BasePresenter;
import com.example.iwen.factory.data.helper.InSignUpHelper;
import com.example.iwen.factory.data.helper.SignHelper;
import com.example.iwen.factory.model.api.sign.InSignUpModel;
import com.example.iwen.factory.model.api.sign.SignModel;
import com.example.iwen.factory.model.db.sign.InSignRspModel;
import com.example.iwen.factory.model.db.sign.SignRspModel;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

/**
 * @author : iwen大大怪
 * create : 2020-12-22 13:39
 */
public class InSignPresenter extends BasePresenter<InSignContract.View>
        implements InSignContract.Presenter, DataSource.Callback<InSignRspModel> {

    public InSignPresenter(InSignContract.View view) {
        super(view);
    }

    @Override
    public void InSign(String collectId) {
        start();
        InSignContract.View view = getView();
        // 构造发起请求的数据模型
        InSignUpModel inSignUpModel = new InSignUpModel(collectId);
        // 发起网络请求
        InSignUpHelper.InSign(inSignUpModel,this);
    }

    @Override
    public void onDataLoaded(final InSignRspModel inSignRspModel) {
        // 得到View接口
        final InSignContract.View view = getView();
        if (view != null) {
            // 此时是从网络回送回来的，并不保证处于主线程状态
            // 进行线程切换
            Run.onUiAsync(new Action() {
                @Override
                public void call() {
                    // 调用成功
                    view.InSignSuccess(inSignRspModel);
                }
            });
        }
    }

    @Override
    public void onDataNotAvailable(final int strRes) {
        // 网络请求告知网络失败
        // 得到View接口
        final InSignContract.View view = getView();
        if (view == null)
            return;
        // 此时是从网络回送回来的，并不保证处于主线程状态
        // 进行线程切换
        Run.onUiAsync(new Action() {
            @Override
            public void call() {
                // 网络失败
                //view.signFailure(strRes);
                view.showError(strRes);
            }
        });
    }
}
