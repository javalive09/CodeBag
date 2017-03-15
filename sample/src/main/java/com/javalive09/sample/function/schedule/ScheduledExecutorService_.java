package com.javalive09.sample.function.schedule;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by peter on 2017/3/15.
 */

public class ScheduledExecutorService_ extends Entry {

    ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
    long delay = 1000; // milliseconds
    long period = 1000; // milliseconds
    int count = 0;

    public void schedule() {
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LogUtil.i("count =" + count++);
            }
        }, delay, period, TimeUnit.MILLISECONDS);
    }

    public void cancel() {
        service.shutdownNow();
    }

}
