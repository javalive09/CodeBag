package com.javalive09.demos;

import android.os.AsyncTask;
import android.util.Log;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

public class ThreadPool {

    @Run(name = "peter")
    private void run(CodeActivity activity) {
        Object o = new Object();
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("peter", (String)o);
            }
        });
        activity.showText("111111");
    }

}
