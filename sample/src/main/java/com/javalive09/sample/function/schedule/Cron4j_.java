package com.javalive09.sample.function.schedule;

import android.text.TextUtils;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;

import java.util.Calendar;

import it.sauronsoftware.cron4j.Scheduler;

/**
 * Created by peter on 2017/3/15.
 * <p>
 * +---------------- minute (0 - 59)
 * |  +------------- hour (0 - 23)
 * |  |  +---------- day of month (1 - 31)
 * |  |  |  +------- month (1 - 12)
 * |  |  |  |  +---- day of week (0 - 6) (Sunday=0 or 7)
 * |  |  |  |  |
 * *  *  *  *  *  command to be executed
 */

public class Cron4j_ extends Entry {

    private Scheduler scheduler = new Scheduler();
    private String schedulingPattern1 = "30 10 * * *"; // 10:30  everyday
    private String schedulingPattern2 = "* * * * *"; // every minute
    private int count = 0;

    public void schedule() {
        scheduler.schedule(schedulingPattern2, new Runnable() {
            @Override
            public void run() {
                LogUtil.i("count =" + count++);
            }
        });

        scheduler.start();
    }

    public void cancel() {
        scheduler.stop();
    }


    public String scheduleDay() {
        String repeat = "day";
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1489639200 * 1000l);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);
        String pattern = "";
        if (TextUtils.isEmpty(repeat)) {
            pattern = minute + " " + hour + " " + day + " " + month + " *";
        } else {
            if ("day".equals(repeat)) {
                pattern = minute + " " + hour + " *" + " *" + " *";
            } else if ("month".equals(repeat)) {
                pattern = minute + " " + hour + " " + day + " *" + " *";
            } else if ("year".equals(repeat)) {
                pattern = minute + " " + hour + " " + day + " " + month + " *";
            }
        }
        return pattern;
    }

    public String scheduleMonth() {
        String repeat = "month";
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1489639200 * 1000l);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);
        String pattern = "";
        if (TextUtils.isEmpty(repeat)) {
            pattern = minute + " " + hour + " " + day + " " + month + " *";
        } else {
            if ("day".equals(repeat)) {
                pattern = minute + " " + hour + " *" + " *" + " *";
            } else if ("month".equals(repeat)) {
                pattern = minute + " " + hour + " " + day + " *" + " *";
            } else if ("year".equals(repeat)) {
                pattern = minute + " " + hour + " " + day + " " + month + " *";
            }
        }
        return pattern;
    }

    public String scheduleYear() {
        String repeat = "year";
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1489639200 * 1000l);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);
        String pattern = "";
        if (TextUtils.isEmpty(repeat)) {
            pattern = minute + " " + hour + " " + day + " " + month + " *";
        } else {
            if ("day".equals(repeat)) {
                pattern = minute + " " + hour + " *" + " *" + " *";
            } else if ("month".equals(repeat)) {
                pattern = minute + " " + hour + " " + day + " *" + " *";
            } else if ("year".equals(repeat)) {
                pattern = minute + " " + hour + " " + day + " " + month + " *";
            }
        }
        return pattern;
    }


}
