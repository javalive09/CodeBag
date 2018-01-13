package com.javalive09.demos.rxjava;


import android.util.Log;

import com.javalive09.codebag.Play;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

/**
 * Created by peter on 2017/5/3.
 */

public class Connect {

    @Play
    public void publish() {
        ConnectableObservable publish = Observable.just(1, 2, 3, 4, 5).publish();
        publish.subscribe(s -> Log.i("publish", s + ""));
        publish.connect();
    }

    @Play
    public void refCount() {
        ConnectableObservable publish = Observable.just(1, 2, 3, 4, 5).publish();
        publish.refCount().subscribe(s -> Log.i("refCount", s + ""));
    }

    @Play
    public void replay() {
        Observable o = Observable.just(1, 2, 3, 4, 5);
        o.subscribe(s -> Log.i("replay", s + ""));

        ConnectableObservable connectableObservable = o.replay();
        connectableObservable.subscribe(s -> Log.i("replay", s + ""));
        connectableObservable.connect();
    }


}
