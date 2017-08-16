package com.example.admin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.admin.services.Services.TestService;


/**
 * Created by Admin on 8/15/2017.
 */

public class MusicReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        switch(intent.getAction()){

            case "StopTask":
                context.stopService(new Intent(context, TestService.class));
                break;
        }

    }
}
