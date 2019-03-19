package com.module.applive.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.module.applive.utils.ServiceMangerUtils;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ScheduleService extends JobService {

    private static final String TAG = "ScheduleService";

    private JobScheduler mJobScheduler;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startJobScheduler(startId);
        return START_STICKY;
    }

    public void startJobScheduler(int id) {
        mJobScheduler.cancel(id);
        JobInfo.Builder builder = new JobInfo.Builder(id, new ComponentName(getPackageName(), ScheduleService.class.getName()));
        if (Build.VERSION.SDK_INT >= 24) {
            builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS); //执行的最小延迟时间
            builder.setOverrideDeadline(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);  //执行的最长延时时间
            builder.setMinimumLatency(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS);
            builder.setBackoffCriteria(JobInfo.DEFAULT_INITIAL_BACKOFF_MILLIS, JobInfo.BACKOFF_POLICY_LINEAR);//线性重试方案
        }
        builder.setPeriodic(5000);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        JobInfo info = builder.build();
        mJobScheduler.schedule(info); //开始定时执行该系统任务
    }

    @Override
    public boolean onStartJob(JobParameters params) {
      Log.d(TAG, "onStartJob(): params = [" + params + "]");
      if(!ServiceMangerUtils.isServiceWorked(this, "com.module.applive.service.ForgroundService")){
          Intent intent = new Intent(this, ForgroundService.class);
          startService(intent);
      }
      jobFinished(params, false);
      return false;
    }

     @Override
     public boolean onStopJob(JobParameters params) {    
       Log.d(TAG, "onStopJob(): params = [" + params + "]");    
       return false;
    }
}