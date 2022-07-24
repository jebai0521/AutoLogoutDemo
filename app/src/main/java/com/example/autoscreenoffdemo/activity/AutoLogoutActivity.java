package com.example.autoscreenoffdemo.activity;

import android.os.Bundle;

import com.example.autoscreenoffdemo.R;
import com.example.autoscreenoffdemo.autologout.IAutoLogout;

public class AutoLogoutActivity extends BaseActivity  implements IAutoLogout {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_logout);
        setTitle("自动登出");
    }
}