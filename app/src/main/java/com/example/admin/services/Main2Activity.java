package com.example.admin.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.admin.services.Services.MyBoundService;
import com.example.admin.services.Services.boundStringService;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = "Main2Activity" ;
    MyBoundService boundService;
    boundStringService stringService;
    String num;
    int randnum;
    ArrayList<String> strings = new ArrayList();
    TextView tvNum;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    StringAdapter stringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView) findViewById(R.id.stringView);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);
        stringAdapter = new StringAdapter(strings);
        recyclerView.setAdapter(stringAdapter);

        tvNum = (TextView) findViewById(R.id.tvNum);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle.getSerializable("data") instanceof String) {
            num = (String) bundle.getSerializable("data");
            Intent boundIntent = new Intent(this, MyBoundService.class);
            bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
        else{
            randnum = (int) bundle.getSerializable("data");
            Intent boundIntent = new Intent(this, boundStringService.class);
            bindService(boundIntent, serviceConnection2, Context.BIND_AUTO_CREATE);
        }


    }


    ServiceConnection serviceConnection2 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            boundStringService.StringBinder binder = (boundStringService.StringBinder) iBinder;
            stringService = binder.getService();
            strings.addAll(stringService.getRandomStrings(randnum));
            tvNum.setText(Integer.toString(randnum));
            while(strings.size() != randnum)
            stringAdapter.notifyDataSetChanged();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

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
