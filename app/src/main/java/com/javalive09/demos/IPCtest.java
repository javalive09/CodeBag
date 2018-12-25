package com.javalive09.demos;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;
import com.javalive09.rxipc.IPCHelper;

import android.os.Bundle;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by peter on 2018/12/18
 */
public class IPCtest {

    @Run
    public void ipc(CodeActivity codeActivity) {
        codeActivity.showText("invoke ipc method...");
        Observable<Bundle> observable = IPCHelper.call(codeActivity, "com.javalive09.ipc", "ipc-test", null, null);
        Disposable disposable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<Bundle>() {
                            @Override
                            public void accept(Bundle bundle) throws Exception {
                                String name = bundle.getString("abc");
                                codeActivity.showText(name);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e("IPC", throwable.getMessage());
                            }
                        });
    }

}
