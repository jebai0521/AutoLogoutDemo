package com.example.autoscreenoffdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.autoscreenoffdemo.autologout.AutoLogoutManager;
import com.example.autoscreenoffdemo.autologout.IAutoLogout;
import com.example.autoscreenoffdemo.util.CrashHandler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DemoApplication extends Application {
    private static final String TAG = "DemoApplication";

    @Override
    protected void attachBaseContext(Context base) {
        Log.i(TAG, "attachBaseContext");
        super.attachBaseContext(base);
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks);
        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    private ActivityLifecycleCallbacks mActivityLifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
            Log.i(TAG, "onActivityCreated:" + activity.getClass().getName());
        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            Log.i(TAG, "onActivityStarted:" + activity.getClass().getName());
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {
            Log.i(TAG, "onActivityResumed:" + activity.getClass().getName());
            if (activity instanceof IAutoLogout) {
                AutoLogoutManager.getInstance().startAutoLogoutPage(activity);
            } else {
                AutoLogoutManager.getInstance().startNonAutoLogoutPage(activity);
            }
        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {
            Log.i(TAG, "onActivityPaused:" + activity.getClass().getName());
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            Log.i(TAG, "onActivityStopped:" + activity.getClass().getName());
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
            Log.i(TAG, "onActivitySaveInstanceState:" + activity.getClass().getName());
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {
            Log.i(TAG, "onActivityDestroyed:" + activity.getClass().getName());
        }
    };
}
