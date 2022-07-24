package com.example.autoscreenoffdemo.activity;

import android.os.Bundle;

import com.example.autoscreenoffdemo.R;

public class NonAutoLogoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_auto_logout);
        this.setTitle("不自动登出");
    }
}