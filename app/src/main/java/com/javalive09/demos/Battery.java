package com.javalive09.demos;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by peter on 2018/11/8
 */
public class Battery {

    private boolean isValidBatterys(Context mContext) {
        Intent batteryInfoIntent = mContext.getApplicationContext()
                .registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        Log.i("peter", "isValidBatterys=" + intent.toString());
                    }
                }, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (batteryInfoIntent != null) {
            int level = batteryInfoIntent.getIntExtra("level", 0);
            int scale = batteryInfoIntent.getIntExtra("scale", 0);
            int rate = (level * 100) / scale;
            Log.d("peter", "level :" + level);
            Log.d("peter", "scale :" + scale);
            Log.d("peter", "rate :" + rate);
            return ((level * 100) / scale) > 50;
        }
        return false;
    }

    @Run
    public void isValidBattery(CodeActivity codeActivity) {
        isValidBatterys(codeActivity);
    }
}
