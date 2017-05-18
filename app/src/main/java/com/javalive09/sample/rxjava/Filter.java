package com.javalive09.sample.rxjava;


import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by peter on 2017/5/2.
 */

public class Filter extends Entry {

    /**
     * 防止按钮重复点击
     */
    public void debounce() {
        Observable.create((Observable.OnSubscribe<Integer>) subscriber -> {
            if (subscriber.isUnsubscribed()) return;
            try {
                for (int i = 1; i < 10; i++) {
                    subscriber.onNext(i);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.newThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(integer -> Log.i(" " + integer));
    }

    public void distinct() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "b"))
                .distinct()
                .subscribe(s -> Log.i(s));
    }

    public void elementAt() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "b"))
                .elementAt(1)
                .subscribe(s -> Log.i(s));
    }

    public void filter() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "b"))
                .filter(s -> s.equals("b"))
                .subscribe(s -> Log.i(s));
    }

    public void first() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "b"))
                .first()
                .subscribe(s -> Log.i(s));
    }

    public void ignoreElements() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            if (subscriber.isUnsubscribed()) return;
            for (int i = 1; i < 10; i++) {
                subscriber.onNext("a" + i);
            }
            subscriber.onCompleted();
            subscriber.onError(new Throwable());
        }).ignoreElements().subscribe(s -> Log.i("end===>" + s));
    }

    public void last() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "i"))
                .last()
                .subscribe(s -> Log.i(s));
    }

    public void sample() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            if (subscriber.isUnsubscribed()) return;
            for (int i = 1; i < 10; i++) {
                subscriber.onNext("a" + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread())
                .sample(400, TimeUnit.MILLISECONDS)
                .subscribe(s -> Log.i(s));
    }

    public void skip() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "i"))
                .skip(2)
                .subscribe(s -> Log.i(s));
    }

    public void skipLast() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "i"))
                .skipLast(2)
                .subscribe(s -> Log.i(s));
    }

    public void take() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "i"))
                .take(2)
                .subscribe(s -> Log.i(s));
    }

    public void takeLast() {
        Observable.from(Arrays.asList("a", "b", "c", "a", "e", "i"))
                .takeLast(2)
                .subscribe(s -> Log.i(s));
    }

}
