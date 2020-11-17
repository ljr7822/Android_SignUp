package com.example.iwen.common.factory.presenter;

/**
 * author : Iwen大大怪
 * create : 2020/11/16 21:08
 */
public class BasePresenter<T extends BaseContract.View>
        implements BaseContract.Presenter {
    protected T mView;

    public BasePresenter(T view) {
        setView(view);
    }

    /**
     * 给子类使用的获取view的操作，子类可以复写完成
     * @return view
     */
    protected final T getView(){
        return mView;
    }

    /**
     * 设置一个View，子类可以复写
     */
    @SuppressWarnings("unchecked")
    protected void setView(T view){
        this.mView = view;
        this.mView.setPresenter(this);
    }

    @Override
    public void start() {
        // 开始的时候进行Loading调用
        T view = mView;
        if (view!=null){
            view.showLoading();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view!= null){
            // 把Presenter设置为空
            view.setPresenter(null);
        }
    }
}
