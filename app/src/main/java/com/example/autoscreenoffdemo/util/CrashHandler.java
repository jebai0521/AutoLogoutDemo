package com.example.autoscreenoffdemo.util;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "CrashHandler";

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
//        Log.e(TAG, "未处理的异常", throwable);
//        Log.e(TAG, Build.VERSION.RELEASE);
//        Log.e(TAG, "" + Build.VERSION.SDK_INT);
//        Log.e(TAG, Build.MANUFACTURER);
//        Log.e(TAG, Build.MODEL);
        String stacktrace = Log.getStackTraceString(throwable);
        Log.i(TAG, "stacktrace:" + stacktrace);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
