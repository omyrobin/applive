package com.module.applive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.module.applive.service.ForgroundService;
import com.module.applive.service.LocationService;
import com.module.applive.service.RemoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //前台服务
//        startService(new Intent(this, ForgroundService.class));

        //双进程守护
        startService(new Intent(this, LocationService.class));
        startService(new Intent(this, RemoteService.class));
    }
}
