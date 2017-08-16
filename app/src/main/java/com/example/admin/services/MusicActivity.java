package com.example.admin.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.example.admin.MusicReceiver;
import com.example.admin.services.Services.TestService;

public class MusicActivity extends AppCompatActivity {

    Intent musicIntent;
    NotificationManager notificationManager;
    MusicReceiver musicReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        musicIntent = new Intent(this, TestService.class);
        intentFilter = new IntentFilter();

    }

    public void startMusic(View view){

        startService(musicIntent);
        startNotification(view);
    }

    public void startNotification(View view){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_audiotrack_black_24dp);
        Intent intent = new Intent();
        intent.setAction("StopTask");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("Music");
        builder.addAction(R.drawable.ic_stop_black_24dp, "Stop", pendingIntent);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1, builder.build());
    }
    public void cancelNotification(View view){
        notificationManager.cancel(1);
    }

    public void stopMusic(View view){stopService(musicIntent);}
}
