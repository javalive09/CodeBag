package com.javalive09.demos;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;
import com.javalive09.rxipc.IPCHelper;
import android.text.TextUtils;
import android.util.Log;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by peter on 2018/12/18
 */
public class IPCtest {

    @Run
    public void ipc(CodeActivity codeActivity) {
        codeActivity.showText("invoke ipc method...");
        Disposable d = IPCHelper.call(codeActivity, "com.javalive09.ipc", "ipc-test", null, null)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Log.e("IPC", throwable.getMessage())).subscribe(bundle -> {
                    String name = bundle.getString("abc");
                    if (!TextUtils.isEmpty(name)) {
                        codeActivity.showText(name);
                    }
                });
    }

}
