package com.example.autoscreenoffdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.autoscreenoffdemo.R;
import com.example.autoscreenoffdemo.autologout.IAutoLogout;

public class HomeActivity extends BaseActivity implements IAutoLogout {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.auto_logout).setOnClickListener(this::onJumpToAutoLogout);
        findViewById(R.id.non_auto_logout).setOnClickListener(this::onJumpToNonAutoLogout);
        setTitle("首页");
    }

    private void onJumpToAutoLogout(View view) {
        startActivity(new Intent(this, AutoLogoutActivity.class));
    }

    private void onJumpToNonAutoLogout(View view) {
        startActivity(new Intent(this, NonAutoLogoutActivity.class));
    }
}