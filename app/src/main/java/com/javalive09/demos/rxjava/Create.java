package com.javalive09.demos.rxjava;

import android.util.Log;

import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableCreate;

/**
 * 创建
 */

@Player(name = "Create 创建")
public class Create {

    @Play
    public void create() {
        new ObservableCreate<>(e -> {
            e.onNext("a");
            e.onNext("b");
            e.onNext("c");
            e.onNext("d");
            e.onNext("e");
            e.onComplete();
        }).subscribe(o -> Log.i("create", o + ""));

        Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onNext("e");
            subscriber.onComplete();
        }).subscribe(t -> {
            Log.i("create", t + "");
        });
    }

    @Play(name = "Just \n将单个数据转换为发射那个数据的Observable")
    public void just() {
        Observable.just("a").subscribe(s -> Log.i("create", s + ""));
    }

    @Play(name = "defer\n 直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable")
    public void defer() {
        Observable.defer(() -> Observable.just(System.currentTimeMillis())).subscribe(s -> Log.i("Create", "defer = " + s));
    }

    @Play(name = "fromArray \n 将数组数据类型转换为Observable")
    public void fromArray() {
        Observable.fromArray("a", "b", "c", "d", "e").subscribe(s -> Log.i("Create", "from = " + s));
    }

    @Play(name = "interval \n 创建一个按固定时间间隔发射整数序列的Observable")
    public void interval() {
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.i("Create", "interval = " + aLong));
        Observable.just("").delay(10, TimeUnit.SECONDS).subscribe(s -> disposable.dispose());
    }

    @Play(name = "range \n 创建一个发射特定整数序列的Observable")
    public void range() {
        Observable.range(3, 5).subscribe(integer -> Log.i("Create", "range = " + integer));
    }

    @Play(name = "timer \n 创建一个Observable，它在一个给定的延迟后发射一个特殊的值")
    public void timer() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(aLong -> Log.i("Create", "start = " + aLong));
    }


}
