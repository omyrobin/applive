package com.module.applive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.module.applive.service.LocationService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //前台服务
//        startService(new Intent(this, ForgroundService.class));

        //双进程守护
        startService(new Intent(this, LocationService.class));
//        startService(new Intent(this, RemoteService.class));
    }
}
