package com.javalive09.demos;

import com.javalive09.rxipc.ICallee;
import com.javalive09.rxipc.IPCHelper;

import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by peter on 2018/12/25
 */
public class App extends Application {

    boolean start = false;

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (start) {
                    Observable observable = IPCHelper.call(getApplicationContext(), "com.javalive09.ipc", "ipc_test",
                            null, null);
                    observable.subscribeOn(Schedulers.io()).subscribe(new Consumer() {
                        @Override
                        public void accept(Object o) throws Exception {

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("peter", throwable.toString());
                        }
                    });
                    SystemClock.sleep(60);
                }
            }
        }).start();

        IPCHelper.registerCallee(new ICallee() {
            @Override
            public Bundle onCall(@NonNull String s, @Nullable String s1, @Nullable Bundle bundle) {
                Log.i("peter","com.javalive09.demos onCall");
                return null;
            }
        }, "ipc_test");

    }

}
