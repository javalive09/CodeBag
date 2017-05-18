package com.javalive09.sample.rxjava;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import rx.Observable;

/**
 * Created by peter on 2017/5/2.
 */

public class Error extends Entry {

    public void retry() {
        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onError(new Throwable());
            subscriber.onNext("e");
            subscriber.onCompleted();
        }).retry(3).subscribe(t -> Log.i("time = " + t));
    }



}
