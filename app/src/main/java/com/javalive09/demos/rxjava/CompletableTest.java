package com.javalive09.demos.rxjava;

import java.util.concurrent.Callable;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.util.Log;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by peter on 2019-05-24
 */
public class CompletableTest {

    @Run
    public void action(CodeActivity activity) {
        Completable.fromAction(() -> {
            String name = Thread.currentThread().getName();
            Log.i("peter", "name1-----" + name);
        }).subscribeOn(Schedulers.io()).subscribe();
        Completable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                String name = Thread.currentThread().getName();
                Log.i("peter", "name2-----" + name);
                return null;
            }
        }).observeOn(Schedulers.io()).subscribe();
        Completable.fromRunnable(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                Log.i("peter", "name3-----" + name);
            }
        }).subscribe();

        Maybe.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                String name = Thread.currentThread().getName();
                Log.i("peter", "name4-----" + name);
            }
        }).subscribe();
        activity.showText("====");
    }

}
