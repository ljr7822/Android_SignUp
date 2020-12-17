package com.example.iwen.singup.fragment.account;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iwen.common.app.PresenterFragment;
import com.example.iwen.factory.model.db.account.PersonalRecordRspModel;
import com.example.iwen.factory.presenter.account.PersonalRecordContract;
import com.example.iwen.factory.presenter.location.GetLocationTaskListContract;
import com.example.iwen.singup.R;

import java.util.List;

/**
 * 签到记录的fragment
 */
public class PersonalRecordFragment extends PresenterFragment<PersonalRecordContract.Presenter>
        implements PersonalRecordContract.View {

    public PersonalRecordFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_personal_record;
    }

    @Override
    protected PersonalRecordContract.Presenter initPresenter() {
        return null;
    }

    @Override
    public void getPersonalRecordSuccess(List<PersonalRecordRspModel> personalRecordRspModelList) {

    }
}