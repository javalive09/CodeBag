package com.javalive09.demos;

import android.os.SystemClock;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

public class Reminder {

    private int T = 60;

    @Run
    public void show(CodeActivity codeActivity) {
        new Thread(() -> {
            for(long i= 1234; i < Long.MAX_VALUE; i ++) {
                long j = i % T;
                long offset = (i + 3) % T;

                String s = i + "--" + j + "\n"
                        + i  + "--" + offset;

                codeActivity.runOnUiThread(() -> codeActivity.showText(s));
                SystemClock.sleep(1000);
            }
        }).start();
        codeActivity.showText("null");
    }


}
