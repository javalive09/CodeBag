package com.javalive09.demos.rxjava;


import android.util.Log;

import com.javalive09.codebag.Play;

import io.reactivex.Observable;

/**
 * 错误处理
 */

public class Error {

    @Play
    public void retry() {
        Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onError(new Throwable());
            subscriber.onNext("e");
            subscriber.onComplete();
        }).retry(3).subscribe(t -> Log.i("retry", "time = " + t));
    }



}
