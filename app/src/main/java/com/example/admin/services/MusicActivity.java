package com.example.admin.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.admin.services.Services.MyMusicService;
import com.example.admin.services.Services.TestService;

public class MusicActivity extends AppCompatActivity {

    Intent musicIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        musicIntent = new Intent(this, TestService.class);
    }

    public void startMusic(View view){
        startService(musicIntent);
    }
    public void stopMusic(View view){stopService(musicIntent);}
}
