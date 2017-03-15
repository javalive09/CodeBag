package com.javalive09.sample.function.schedule;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by peter on 2017/3/15.
 */

public class Timer_ extends Entry {

    Timer timer = new Timer();
    long delay = 1000; // milliseconds
    long period = 1000; // milliseconds
    int count = 0;

    public void schedule() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                LogUtil.i("count =" + count++);
            }
        }, delay, period);

    }

    public void cancel() {
        timer.cancel();
    }

}
