package com.example.iwen.singup.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.iwen.singup.R;
import com.example.iwen.singup.helper.TimeThread;

import butterknife.BindView;

/**
 * 签到记录的Activity
 */
public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        // TODO 加载签到记录

        TextView mNowDate = findViewById(R.id.tv_DateNow);
        TimeThread timeThread = new TimeThread(mNowDate);
        timeThread.start();
    }
}