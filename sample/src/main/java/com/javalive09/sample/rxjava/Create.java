package com.javalive09.sample.rxjava;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by peter on 2017/4/29.
 */

public class Create extends Entry {

    public void create() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onNext("e");
            subscriber.onCompleted();
        }).subscribe(t -> Log.i("time = " + t));
    }

    public void just() {

    }

    public void defer() {
        Observable.defer(() -> Observable.just(System.currentTimeMillis())).subscribe(s -> Log.i("defer = " + s));
    }

    public void from() {
        Observable.from(Arrays.asList("a", "b", "c", "d", "e")).subscribe(s -> Log.i("from = " + s));
    }

    public void interval() {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(aLong -> Log.i("interval = " + aLong));
    }

    public void range() {
        Observable.range(3, 5).subscribe(integer -> Log.i("range = " + integer));
    }

    public void start() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> Log.i("start = " + aLong));
    }


}
