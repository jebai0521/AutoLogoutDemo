package com.example.autoscreenoffdemo.activity;

import android.util.Log;

import com.example.autoscreenoffdemo.autologout.AutoLogoutManager;
import com.example.autoscreenoffdemo.autologout.IAutoLogout;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.i(TAG, "onUserInteraction" + this.getClass().getName());
        if (this instanceof IAutoLogout) {
            AutoLogoutManager.getInstance().onUserInteraction();
        }
    }
}
