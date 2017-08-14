package com.example.admin.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.admin.services.Services.MyBoundService;
import com.example.admin.services.Services.MyIntentService;
import com.example.admin.services.Services.MyNormalService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    MyBoundService boundService;
    Boolean isBound = false;
    EditText etNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNum = (EditText) findViewById(R.id.etNum);
    }

    public void startService(View view){
        Intent normalIntent = new Intent(this, MyNormalService.class);
        Intent intIntent = new Intent(this, MyIntentService.class);

        Intent boundIntent = new Intent(this, MyBoundService.class);

        switch(view.getId()){
            case R.id.btnStartNormalService:
                normalIntent.putExtra("data", "This is a normal service");
                startService(normalIntent);
                break;
            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;
            case R.id.btnStartIntentService:
                intIntent.putExtra("data", "This is an intent service");
                //intIntent.setAction("getRepo");
                startService(intIntent);
                break;
            case R.id.btnBindService:
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                if(isBound) {
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;
            case R.id.btnSendInt:
                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("data", etNum.getText().toString());
                startActivity(intent);
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            boundService = myBinder.getService();
            isBound = true;
            Log.d(TAG, "onServiceConnected: "+boundService.getRandomData());

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };
}
