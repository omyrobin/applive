package com.module.applive.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by wubo on 2019/3/18.
 */

public class ForgroundService extends Service {

    public static final int NOTIFICATION_ID = 0x11;

    private class LocationBinder extends Binder {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocationBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Log.i("TAG", "我在上传数据");
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            startForeground(NOTIFICATION_ID, new Notification());
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID, new Notification());
            startService(new Intent(this, InnerService.class));
        }else{
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("channel", "xxx", NotificationManager.IMPORTANCE_MIN);
            if(null != manager){
                manager.createNotificationChannel(channel);
                Notification notification = new NotificationCompat.Builder(this,"channel;").build();
                startForeground(NOTIFICATION_ID, notification);
            }

        }
        return START_STICKY;
    }

    public static class InnerService extends Service {
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(NOTIFICATION_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }
}
