package com.javalive09.sample.function.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 *
 * Created by peter on 2017/3/13.
 *
 */

public class AlarmClockReceiver extends BroadcastReceiver {

    public static final String ACTION_ALARM = "action_alarm_clock";
    public static final String ACTION_ALARM_CANCEL = "action_alarm_clock_cancel";

    @Override
    public void onReceive(final Context context, Intent intent) {
        String action = intent.getAction();
        if(Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, "boot completed alarm", Toast.LENGTH_LONG).show();
                }
            });

        }else if(ACTION_ALARM.equals(action)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, "play alarm", Toast.LENGTH_LONG).show();
                }
            });
        }else if(ACTION_ALARM_CANCEL.equals(action)) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, "cancel alarm", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

}
