package com.example.admin.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
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
import com.example.admin.services.Services.MyJobService;
import com.example.admin.services.Services.MyNormalService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    MyBoundService boundService;
    int randnum;
    boolean isBound = false;
    EditText etNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etNum = (EditText) findViewById(R.id.etNum);
        Intent boundIntent = new Intent(this, MyBoundService.class);
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void startService(View view){
        Intent normalIntent = new Intent(this, MyNormalService.class);
        Intent intIntent = new Intent(this, MyIntentService.class);
        Intent intent = new Intent(this, Main2Activity.class);
        Intent musicIntent = new Intent(this, MusicActivity.class);

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
                if(!isBound) {
                    bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                }
                break;
            case R.id.btnUnbindService:
                if(isBound) {
                    unbindService(serviceConnection);
                    isBound = false;
                }
                break;
            case R.id.btnSendInt:
                final Bundle bundle = new Bundle();
                bundle.putSerializable("data", etNum.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btnRandomStrings:
                Bundle bund = new Bundle();
                bund.putSerializable("data", randnum);
                intent.putExtras(bund);
                startActivity(intent);
                break;
            case R.id.btnGoToMusic:
                startActivity(musicIntent);
                break;
            case R.id.btnScheduleService:
                ComponentName serviceComponent = new ComponentName(this, MyJobService.class);
                JobInfo.Builder jobInfo = new JobInfo.Builder(0,serviceComponent);

                jobInfo.setMinimumLatency(100);
                if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
                    Log.d(TAG, "Is Supported");
                    JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                    //JobScheduler jobScheduler = getSystemService(JobScheduler.class);
                    jobScheduler.schedule(jobInfo.build());
                }
                else{
                    Log.d(TAG, "Not Supported");
                }
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            boundService = myBinder.getService();
            randnum = boundService.getRandomData();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };
}
