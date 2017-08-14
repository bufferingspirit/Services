package com.example.admin.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.admin.services.Services.MyBoundService;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity" ;
    MyBoundService boundService;
    String num;
    TextView tvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tvNum = (TextView) findViewById(R.id.tvNum);
        Intent boundIntent = new Intent(this, MyBoundService.class);
        Intent intent = getIntent();
        num = intent.getStringExtra("data");
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);


    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            boundService = myBinder.getService();
            Log.d(TAG, "onServiceConnected: ");
            tvNum.setText(Integer.toString(boundService.sumNum(Integer.parseInt(num))));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
