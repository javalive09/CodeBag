package com.javalive09.demos;

import com.javalive09.rxipc.IPCHelper;

import android.app.Application;
import android.os.SystemClock;
import android.util.Log;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by peter on 2018/12/25
 */
public class App extends Application {

    boolean start = false;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(() -> {
            while (start) {
                IPCHelper.call(getApplicationContext(), "com.javalive09.ipc", "ipc_test",
                        null, null).subscribeOn(Schedulers.io()).doOnError(
                        throwable -> Log.e("peter", throwable.toString())).subscribe();
                SystemClock.sleep(60);
            }
        }).start();

        IPCHelper.registerCallee((s, s1, bundle) -> {
            Log.i("peter","com.javalive09.demos onCall");
            return null;
        }, "ipc_test");

    }

}
