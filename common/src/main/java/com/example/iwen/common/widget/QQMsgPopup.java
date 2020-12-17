package com.example.iwen.common.widget;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.iwen.common.R;
import com.lxj.xpopup.core.PositionPopupView;

/**
 * @author : Iwen大大怪
 * create : 11-21 021 16:08
 */
public class QQMsgPopup extends PositionPopupView {
    public QQMsgPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.popup_qq_msg;
    }
}
