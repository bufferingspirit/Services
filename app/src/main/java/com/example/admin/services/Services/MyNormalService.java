package com.example.admin.services.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyNormalService extends Service {
    public static final String TAG = "MyNormalServiceTAG";

    public MyNormalService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate :");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String out = intent.getStringExtra("data");
        Log.d(TAG, "onStartCommand: " + out);

        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(TAG, "onDestroy: ");
    }
}
