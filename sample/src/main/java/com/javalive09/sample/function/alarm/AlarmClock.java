package com.javalive09.sample.function.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.javalive09.codebag.Entry;

import java.util.Calendar;

/**
 * Created by peter on 2017/3/13.
 */

public class AlarmClock extends Entry {

    private static final int INTERVAL = 1000 * 60 * 60 * 24;// 24h

    public void setClock() {

        Intent intent = new Intent(getActivity(), AlarmClockReceiver.class);
        intent.setAction("abc");
        PendingIntent sender = PendingIntent.getBroadcast(getActivity(),
                1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Schedule the alarm!
        AlarmManager am = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 52);
        calendar.set(Calendar.SECOND, 1);
        calendar.set(Calendar.MILLISECOND, 1);

        long time = calendar.getTimeInMillis();
        long t = System.currentTimeMillis();
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, INTERVAL, sender);
    }


}
