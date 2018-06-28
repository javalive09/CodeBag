package com.javalive09.demos.rxjava;


import android.util.Log;

import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Code(name = "Filter 过滤")
public class Filter {

    @Run(name = "debounce \n仅在过了一段指定的时间还没发射数据时才发射一个数据，可用于防止按钮重复点击")
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
                .subscribe(integer -> Log.i("Filter", " " + integer));
    }

    @Run(name = "distinct \n 过滤掉重复的数据项 去重")
    public void distinct() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .distinct()
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "distinctUntilChanged \n只判定一个数据和它的直接前驱是否是不同的 可以间隔相同")
    public void distinctUntilChanged() {
        Observable.fromArray("a", "b", "c", "a", "e", "b").
                distinctUntilChanged()
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "elementAt \n只发射第N项数据")
    public void elementAt() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .elementAt(1)
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "filter \n只发射通过了测试的数据项")
    public void filter() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .filter(s -> s.equals("b"))
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "first \n 只发射第一项（或者满足某个条件的第一项）数据")
    public void first() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .first("")
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "ignoreElements \n不发射任何数据，只发射Observable的终止通知")
    public void ignoreElements() {
        Observable.create(subscriber -> {
            if (subscriber.isDisposed()) return;
            for (int i = 1; i < 10; i++) {
                subscriber.onNext("a" + i);
            }
            subscriber.onComplete();
        }).ignoreElements().subscribe(() -> Log.i("Filter", "end===>"));
    }

    @Run(name = "ofType \n 过滤一个Observable只返回指定类型的数据")
    public void ofType() {
        Observable.fromArray("a", "b", "c", "a", "e", "b")
                .ofType(String.class)
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "last \n只发射最后一项（或者满足某个条件的最后一项）数据")
    public void last() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .last("")
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "sample \n定期发射Observable最近发射的数据项 采样")
    public void sample() {
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS).
                subscribeOn(Schedulers.newThread())
                .sample(3, TimeUnit.SECONDS)
                .subscribe(s -> Log.i("Filter", s.toString()));

        Observable.timer(10, TimeUnit.SECONDS).subscribe(aLong -> disposable.dispose());
    }

    @Run(name = "throttleFirst \n定期发射Observable发射的第一项数据")
    public void throttleFirst() {
        Disposable disposable = Observable.interval(100, TimeUnit.MILLISECONDS).
                subscribeOn(Schedulers.newThread())
                .throttleFirst(400, TimeUnit.MILLISECONDS)
                .subscribe(s -> Log.i("Filter", s.toString()));

        Observable.timer(10, TimeUnit.SECONDS).subscribe(aLong -> disposable.dispose());
    }

    @Run(name = "skip \n忽略Observable发射的前N项数据")
    public void skip() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .skip(2)
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "skipLast \n忽略Observable发射的后N项数据")
    public void skipLast() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .skipLast(2)
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "take \n 只发射前面的N项数据")
    public void take() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .take(2)
                .subscribe(s -> Log.i("Filter", s));
    }

    @Run(name = "takeLast \n 发射Observable发射的最后N项数据")
    public void takeLast() {
        Observable.fromArray("a", "b", "c", "a", "e", "i")
                .takeLast(2)
                .subscribe(s -> Log.i("Filter", s));
    }

}
