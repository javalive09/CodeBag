package com.javalive09.demos.rxjava;


import android.util.Log;

import com.javalive09.codebag.annotation.Test;
import com.javalive09.codebag.annotation.Tester;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

@Tester(name = "Connect 通过连接来触发")
public class Connect {

    @Test(name = "publish \nConnectable Observable: 就是一种特殊的Observable对象，并不是Subscrib的时候就发射数据，而是只有对其应用connect操作符的时候才开始发射数据，所以可以用来更灵活的控制数据发射的时机")
    public void publish() {
        ConnectableObservable publish1 = Observable.just(11, 12, 13, 14, 15).publish();
        publish1.subscribe(s -> Log.i("Connect", s + ""));

        ConnectableObservable publish = Observable.just(1, 2, 3, 4, 5).publish();
        publish.subscribe(s -> Log.i("Connect", s + ""));
        publish.connect();
    }

    @Test(name = "refCount\n 重算 让一个可连接的Observable行为像普通的Observable")
    public void refCount() {
        ConnectableObservable publish = Observable.just(1, 2, 3, 4, 5).publish();
        publish.refCount().subscribe(s -> Log.i("Connect", s + ""));
    }

    @Test(name = "replay \n保证所有的观察者收到相同的数据序列，即使它们在Observable开始发射数据之后才订阅")
    public void replay() {
        Observable o = Observable.just(1, 2, 3, 4, 5);
        o.subscribe(s -> Log.i("Connect", s + ""));

        ConnectableObservable connectableObservable = o.replay();
        connectableObservable.subscribe(s -> Log.i("Connect", s + ""));
        connectableObservable.connect();
    }


}
