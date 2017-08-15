package com.example.admin.services.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class boundStringService extends Service {
    public static final String TAG = "boundStringServiceTag";

    IBinder iBinder = new StringBinder();

    public boundStringService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public class StringBinder extends Binder {

        public  boundStringService getService() {
            return boundStringService.this;
        }

    }

    public ArrayList<String> getRandomStrings(int num){
        ArrayList<String> strings = new ArrayList<>();
        String salt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*(){}:;,.'";
        Random random = new Random();
        String out = "";
        for(int i = 0; i < num; i++){
            int count = random.nextInt(30);
            for(int j = 0; j < count; j++){
                out = out + salt.charAt(random.nextInt(salt.length()-1));
            }
            strings.add(out);
            out = "";
        }
        return strings;
    }

}

