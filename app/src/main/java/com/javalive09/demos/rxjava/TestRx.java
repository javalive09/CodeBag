package com.javalive09.demos.rxjava;

import java.util.Observer;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

import android.os.SystemClock;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by peter on 2019/1/10
 */
public class TestRx {

    @Run
    public void run(CodeActivity codeActivity) {

        runFilter(observable, realRun);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    observable.notifyObservers();
                    SystemClock.sleep(200);
                }
            }
        }).start();

    }

    private void multiRun() {
        Log.e("peter", "rx run");
    }

    private Runnable realRun = new Runnable() {
        @Override
        public void run() {
            multiRun();
        }
    };

    private java.util.Observable observable = new java.util.Observable() {
        @Override
        public synchronized boolean hasChanged() {
            return true;
        }
    };

    private Disposable runFilter(java.util.Observable observable, Runnable realRunnable) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                observable.addObserver(new Observer() {
                    @Override
                    public void update(java.util.Observable o, Object arg) {
                        Log.e("peter", "run");
                        emitter.onNext("abc");
                    }
                });
            }
        }).throttleLast(500, java.util.concurrent.TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        realRunnable.run();
                    }
                });
    }


    @Run
    public void asyncTask(CodeActivity codeActivity) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.i("peter", "/////////");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(5000);
                        emitter.onError(new Throwable("error"));
                    }
                });
            }
        }).retry(5).subscribe();

    }

    @Run
    public void subject(CodeActivity codeActivity) {
        PublishSubject publishSubject = PublishSubject.create();
        Disposable disposable = publishSubject.subscribe();
    }

}
