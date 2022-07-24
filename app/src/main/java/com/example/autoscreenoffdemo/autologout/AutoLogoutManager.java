package com.example.autoscreenoffdemo.autologout;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.example.autoscreenoffdemo.activity.LoginActivity;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;

public class AutoLogoutManager {

    private static final String TAG = "AutoLogoutManager";

    private static final int MSG_AUTO_LOGOUT = 10086;

    // 登陆无操作自动登出超时毫秒数
    private static final int LOGIN_TIMEOUT_MILES = 10 * 1000;

    private volatile static AutoLogoutManager instance;

    private HandlerThread mHandlerThread;

    private MyHandler mHandler;

    private WeakReference<Activity> mTopActivityWeakRef;

    public static AutoLogoutManager getInstance() {
        if (instance == null) {
            synchronized (AutoLogoutManager.class) {
                if (instance == null) {
                    instance = new AutoLogoutManager();
                }
            }
        }
        return instance;
    }

    private AutoLogoutManager() {
        mHandlerThread = new HandlerThread(TAG);
        mHandlerThread.start();
        mHandler = new MyHandler(mHandlerThread.getLooper());
    }

    public void startAutoLogoutPage(Activity topActivity) {
        Log.i(TAG, "startAutoLogoutPage:" + topActivity.getClass().getName());
        if (mTopActivityWeakRef != null) {
            mTopActivityWeakRef.clear();
            mTopActivityWeakRef = null;
        }
        mTopActivityWeakRef = new WeakReference<>(topActivity);
        mHandler.removeMessages(MSG_AUTO_LOGOUT);
        mHandler.sendEmptyMessageDelayed(MSG_AUTO_LOGOUT, LOGIN_TIMEOUT_MILES);
    }

    public void startNonAutoLogoutPage(Activity topActivity) {
        Log.i(TAG, "startNonAutoLogoutPage:" + topActivity.getClass().getName());
        if (mTopActivityWeakRef != null) {
            mTopActivityWeakRef.clear();
            mTopActivityWeakRef = null;
        }
        mHandler.removeMessages(MSG_AUTO_LOGOUT);
    }

    public void onUserInteraction() {
        Log.i(TAG, "onUserInteraction:" + mTopActivityWeakRef.get().getClass().getName());
        mHandler.removeMessages(MSG_AUTO_LOGOUT);
        mHandler.sendEmptyMessageDelayed(MSG_AUTO_LOGOUT, LOGIN_TIMEOUT_MILES);
    }

    private void logout() {
        if (mTopActivityWeakRef == null || mTopActivityWeakRef.get() == null) {
            Log.i(TAG, "logout, activity is null");
            return;
        }
        Log.i(TAG, "logout");
        Activity activity = mTopActivityWeakRef.get();
        activity.runOnUiThread(() -> {
            Intent intent = new Intent(activity, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            activity.startActivity(intent);
            mTopActivityWeakRef.clear();
            mTopActivityWeakRef = null;
        });
    }

    private static class MyHandler extends Handler {
        public MyHandler(@NonNull Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.i(TAG, "handleMessage：" + msg.what);
            switch (msg.what) {
                case MSG_AUTO_LOGOUT:
                    AutoLogoutManager.getInstance().logout();
                    break;
                default:
                    break;
            }
        }
    }
}
