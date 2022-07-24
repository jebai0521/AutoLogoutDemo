package com.example.autoscreenoffdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.autoscreenoffdemo.R;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login).setOnClickListener(this::onLogin);
    }

    private void onLogin(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
