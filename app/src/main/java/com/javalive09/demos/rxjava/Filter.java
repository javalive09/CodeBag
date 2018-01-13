package com.javalive09.demos.rxjava;


import android.util.Log;

import com.javalive09.codebag.Play;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 过滤
 */

public class Filter {

    /**
     * 防止按钮重复点击
     */
    @Play
    public void debounce() {
        Observable.create(subscriber -> {
            if (subscriber.isDisposed()) return;
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
                .subscribe(integer -> Log.i("debounce", " " + integer));
    }

    @Play
    public void distinct() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .distinct()
                .subscribe(s -> Log.i("distinct", s));
    }

    @Play
    public void elementAt() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .elementAt(1)
                .subscribe(s -> Log.i("elementAt", s));
    }

    @Play
    public void filter() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .filter(s -> s.equals("b"))
                .subscribe(s -> Log.i("filter", s));
    }

    @Play
    public void first() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .first("")
                .subscribe(s -> Log.i("first", s));
    }

    @Play
    public void ignoreElements() {
        Observable.create(subscriber -> {
            if (subscriber.isDisposed()) return;
            for (int i = 1; i < 10; i++) {
                subscriber.onNext("a" + i);
            }
            subscriber.onComplete();
            subscriber.onError(new Throwable());
        }).ignoreElements().subscribe(() -> Log.i("ignoreElements", "end===>"));
    }

    @Play
    public void last() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .last("")
                .subscribe(s -> Log.i("last", s));
    }

    @Play
    public void sample() {
        Observable.create(subscriber -> {
            if (subscriber.isDisposed()) return;
            for (int i = 1; i < 10; i++) {
                subscriber.onNext("a" + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            subscriber.onComplete();
        }).subscribeOn(Schedulers.newThread())
                .sample(400, TimeUnit.MILLISECONDS)
                .subscribe(s -> Log.i("sample", s.toString()));
    }

    @Play
    public void skip() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .skip(2)
                .subscribe(s -> Log.i("skip", s));
    }

    @Play
    public void skipLast() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .skipLast(2)
                .subscribe(s -> Log.i("skipLast", s));
    }

    @Play
    public void take() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .take(2)
                .subscribe(s -> Log.i("take", s));
    }

    @Play
    public void takeLast() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .takeLast(2)
                .subscribe(s -> Log.i("takeLast", s));
    }

}
