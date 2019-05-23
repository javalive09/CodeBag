package com.javalive09.demos;

import java.util.Random;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by peter on 2019/2/15
 */
public class TimeTest {

    @Run
    public void HeartBeat(CodeActivity codeActivity) {
        codeActivity.showText(getRandomHeartBeatTime() / 1000 / 60 + "min");
    }

    private long getRandomHeartBeatTime() {
        int randomMin = new Random().nextInt(40);
        long randomMillis = randomMin * 60 * 1000;
        long heartBeatTime = 20 * 60 * 1000 + randomMillis;
        return heartBeatTime;
    }

    long randomHeartBeatTime = getRandomHeartBeatTime();

    long start = 1550228880000L;

    @Run
    public void remainder(CodeActivity codeActivity) {

        final long one = 60 * 1000;

        final Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = start + one;

                String re = getremainder(start) + "";

                codeActivity.showText(re);
                handler.postDelayed(this, 100);
            }
        }, 100);
        codeActivity.showText("");

    }

    private long getremainder(long millis) {
        long seconds = millis / 1000;
        long mins = seconds / 60;
        long remainder = mins % (randomHeartBeatTime / 1000 / 60);
        return remainder;
    }

}
