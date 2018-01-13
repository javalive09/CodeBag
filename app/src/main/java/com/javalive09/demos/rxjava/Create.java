package com.javalive09.demos.rxjava;

import android.util.Log;

import com.javalive09.codebag.Play;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * 创建
 */

public class Create {

    @Play
    public void create() {
        Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onNext("e");
            subscriber.onComplete();
        }).subscribe(t -> Log.i("create","time = " + t));
    }

    public void just() {

    }

    @Play
    public void defer() {
        Observable.defer(() -> Observable.just(System.currentTimeMillis())).subscribe(s -> Log.i("defer","defer = " + s));
    }

    @Play
    public void from() {
        Observable.fromArray("a", "b", "c", "d", "e").subscribe(s -> Log.i("from","from = " + s));
    }

    @Play
    public void interval() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(aLong -> Log.i("interval","interval = " + aLong));
    }

    @Play
    public void range() {
        Observable.range(3, 5).subscribe(integer -> Log.i("range","range = " + integer));
    }

    @Play
    public void start() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> Log.i("start","start = " + aLong));
    }


}
