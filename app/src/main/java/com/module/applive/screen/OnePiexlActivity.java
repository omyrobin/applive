package com.module.applive.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.module.applive.receiver.SrceenReceiver;

/**
 * Created by wubo on 2019/3/18.
 */

public class OnePiexlActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.LEFT|Gravity.TOP);
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = 1;
        layoutParams.height = 1;
        getWindow().setAttributes(layoutParams);

        receiver = new BroadcastReceiver(){

            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(receiver, new IntentFilter("finish"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkScreen();
    }

    /**
     * 检查屏幕状态  isScreenOn为true  屏幕“亮”结束该Activity
     */
    private void checkScreen() {

        PowerManager pm = (PowerManager) OnePiexlActivity.this.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        if (isScreenOn) {
            finish();
        }
    }
}
