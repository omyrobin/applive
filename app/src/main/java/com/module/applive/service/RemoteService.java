package com.module.applive.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by wubo on 2019/3/18.
 */

public class RemoteService extends ForgroundService {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this, LocationService.class), connection, Service.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(RemoteService.this, RemoteService.class));
            bindService(new Intent(RemoteService.this, RemoteService.class), connection, Service.BIND_IMPORTANT);
        }
    };
}
