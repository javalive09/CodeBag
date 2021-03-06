package com.javalive09.demos.rxjava;


import android.util.Log;

import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;
import com.javalive09.codebag.CodeActivity;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

@Code(name = "Connect 通过连接来触发")
public class Connect {

    @Run(name = "publish \nConnectable Observable: 就是一种特殊的Observable对象，并不是Subscrib的时候就发射数据，而是只有对其应用connect操作符的时候才开始发射数据，所以可以用来更灵活的控制数据发射的时机")
    public void publish(CodeActivity codeActivity) {
        ConnectableObservable<Integer> publish1 = Observable.just(11, 12, 13, 14, 15).publish();
        Disposable disposable = publish1.subscribe(s -> Log.i("Connect", s + ""));

        ConnectableObservable<Integer> publish = Observable.just(1, 2, 3, 4, 5).publish();
        Disposable disposable2 = publish.subscribe(s -> Log.i("Connect", s + ""));
        publish.connect();
    }

    @Run(name = "refCount\n 重算 让一个可连接的Observable行为像普通的Observable")
    public void refCount(CodeActivity codeActivity) {
        ConnectableObservable<Integer> publish = Observable.just(1, 2, 3, 4, 5).publish();
        Disposable disposable = publish.refCount().subscribe(s -> Log.i("Connect", s + ""));
    }

    @Run(name = "replay \n保证所有的观察者收到相同的数据序列，即使它们在Observable开始发射数据之后才订阅")
    public void replay(CodeActivity codeActivity) {
        Observable<Integer> o = Observable.just(1, 2, 3, 4, 5);
        Disposable disposable = o.subscribe(s -> Log.i("Connect", s + ""));

        ConnectableObservable<Integer> connectableObservable = o.replay();
        Disposable disposable2 = connectableObservable.subscribe(s -> Log.i("Connect", s + ""));
        connectableObservable.connect();
    }


}
