package com.javalive09.sample.rxjava;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by peter on 2017/5/3.
 */

public class Assist extends Entry {

    public void delay() {
        Observable.just(1, 2, 3, 4, 5).delay(1000, TimeUnit.MILLISECONDS).subscribe(s -> Log.i(s + ""));
    }

    public void observerOn() {
        Observable.just(1, 2, 3, 4, 5).observeOn(Schedulers.newThread()).subscribe(s -> Log.i(s + ""));
    }

    public void serialize() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).serialize().subscribe(s -> Log.i(s + ""));
    }

    public void subscribeOn() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).subscribeOn(Schedulers.computation()).subscribe(s -> Log.i(s + ""));
    }

    public void timeInterval() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).timeInterval().subscribe(s -> Log.i(s + ""));
    }

    public void timeout() {
        Observable.just(1, 2, 3, 4, 5).delay(1000, TimeUnit.MILLISECONDS).timeout(500, TimeUnit.MILLISECONDS)
                .subscribe(s -> Log.i(s + ""), throwable -> Log.e(throwable.toString()));
    }

    public void timestamp() {
        Observable.just(1, 2, 3, 4, 5).timestamp().subscribe(s -> Log.i(s + ""));
    }

}
