package com.example.admin.services.Services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Admin on 8/15/2017.
 */

public class MyJobService extends JobService {
    public static final String TAG = "JobService";


    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        Log.d(TAG, "onStartJob: ");

        Intent intent = new Intent(getApplicationContext(), MyScheduleService.class);
        getApplicationContext().startService(intent);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        return false;
    }
}
