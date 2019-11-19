package com.javalive09.demos;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

public class HandlerTest {

    @Run
    public void threadHandler(CodeActivity codeActivity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(5);

                // 不加参数 会crash
                Handler handler = new Handler(Looper.getMainLooper());
                Looper looper = handler.getLooper();
                Log.i("peter", "looper=" + looper);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        codeActivity.showText("handler");
                    }
                });
                SystemClock.sleep(5);
            }
        }).start();
        codeActivity.showText("...");
    }

}
