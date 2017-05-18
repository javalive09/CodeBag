package com.javalive09.sample.rxjava;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * Created by peter on 2017/5/3.
 */

public class Connect extends Entry {

    public void publish() {
        ConnectableObservable publish = Observable.just(1,2,3,4,5).publish();
        publish.subscribe(s -> Log.i(s + ""));
        publish.connect();
    }

    public void refCount() {
        ConnectableObservable publish = Observable.just(1,2,3,4,5).publish();
        publish.refCount().subscribe(s -> Log.i(s+ ""));
    }

    public void replay() {
        Observable o = Observable.just(1,2,3,4,5);
        o.subscribe(s -> Log.i(s + ""));

        ConnectableObservable connectableObservable = o.replay();
        connectableObservable.subscribe(s -> Log.i(s + ""));
        connectableObservable.connect();
    }


}
