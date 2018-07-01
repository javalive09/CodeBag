package com.javalive09.demos.rxjava;

import android.util.Log;

import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Code(name = "Assist 辅助操作")
public class Assist {

    @Run(name = "delay 延迟执行")
    public void delay() {
        Disposable disposable = Observable.just(1, 2, 3, 4, 5).timestamp().delay(1000, TimeUnit.MILLISECONDS).subscribe(s -> Log.i("Assist", s + ""));
    }

    @Run(name = "切换线程（切换接收事件线程）")
    public void observerOn() {
        Disposable disposable = Observable.just(1, 2, 3, 4, 5).observeOn(Schedulers.newThread()).subscribe(s -> Log.i("Assist", s + ""));
    }

    /**
     * http://sherlockshi.github.io/2016/10/17/15_Android/1560_RxJava/RxJava%E6%93%8D%E4%BD%9C%E7%AC%A6%E5%AE%9E%E8%B7%B5%EF%BC%9A6_%E8%BE%85%E5%8A%A9%E6%93%8D%E4%BD%9C%E4%B9%8B4_serialize/
     */
    @Run(name = "serialize \n一个Observable可以异步调用它的观察者的方法，可能是从不同的线程调用。这可能会让Observable行为不正确，它可能会在某一个onNext调用之前尝试调用onCompleted或onError方法，或者从两个不同的线程同时调用onNext方法。使用Serialize操作符，你可以纠正这个Observable的行为，保证它的行为是正确的且是同步的")
    public void serialize() {
        Disposable disposable = Observable.just(1, 3, 2, 4, 5, 8, 6, 7).serialize().subscribe(s -> Log.i("Assist", s + ""));
    }

    @Run(name = "切换线程（切换发出事件线程）")
    public void subscribeOn() {
        Disposable disposable = Observable.just(1, 3, 2, 4, 5, 8, 6, 7).subscribeOn(Schedulers.computation()).subscribe(s -> Log.i("Assist", s + ""));
    }

    /**
     *
     */
    @Run(name = "timeInterval \n这个操作符将原始Observable转换为另一个Observable，后者发射一个标志替换前者的数据项，这个标志表示前者的两个连续发射物之间流逝的时间长度。")
    public void timeInterval() {
        Disposable disposable = Observable.just(1, 3, 2, 4, 5, 8, 6, 7).timeInterval().subscribe(s -> Log.i("Assist", s + ""));
    }

    /**
     *
     */
    @Run(name = "timeout 对原始Observable的一个镜像，如果过了一个指定的时长仍没有发射数据，它会发一个错误通知")
    public void timeout() {
        Disposable disposable = Observable.just(1, 2, 3, 4, 5).delay(1000, TimeUnit.MILLISECONDS).timeout(500, TimeUnit.MILLISECONDS)
                .subscribe(s -> Log.i("Assist", s + ""), throwable -> Log.i("Assist", throwable.toString()));
    }

    /**
     *
     */
    @Run(name = "timestamp 给Observable发射的数据项附加一个时间戳")
    public void timestamp() {
        Disposable disposable = Observable.just(1, 2, 3, 4, 5).timestamp().subscribe(s -> Log.i("Assist", s + ""));
    }

    @Run(name = "doOnEach \n让你可以注册一个回调，它产生的Observable每发射一项数据就会调用它一次")
    public void doOnEach() {
        Disposable disposable = Observable.just(1, 2, 3, 4, 5).doOnEach(integerNotification -> Log.i("Assist", integerNotification + "")).subscribe();
    }

    @Run(name = "doOnNext \n类似于doOnEach(Action1)，但是它的Action不是接受一个Notification参数，而是接受发射的数据项")
    public void doOnNext() {
        Disposable disposable = Observable.just(1, 2, 3, 4, 5).doOnNext(integer -> Log.i("Assist", integer + "")).subscribe();
    }

    @Run(name = "doOnSubscribe \n 注册一个动作，当观察者订阅它生成的Observable它就会被调用")
    public void doOnSubscribe() {
        Disposable disposable1 = Observable.just(1, 2, 3, 4, 5).
                doOnSubscribe(disposable -> Log.i("Assist", disposable + "")).
                subscribe(integer -> Log.i("Assist", integer + ""));
    }

    @Run(name = "doOnDispose \n注册一个动作，当观察者取消订阅它生成的Observable它就会被调用")
    public void doOnDispose() {
        Disposable disposable = Observable.just(1, 2, 3, 4, 5).
                doOnDispose(() -> Log.i("Assist", "disposable")).
                subscribe(integer -> {
                    Log.i("Assist", integer + "");
                });
    }

    @Run(name = "doOnCompleted \n 注册一个动作，当它产生的Observable正常终止调用onCompleted时会被调用")
    public void doOnCompleted() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5).
                doOnComplete(() -> Log.i("Assist", "doOnComplete"));
    }

    @Run(name = "doOnError \n")
    public void doOnError() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5).
                doOnNext(integer -> {
                    if (integer > 3) {
                        throw new Exception("integer > 3 exception!!!");
                    }
                }).
                doOnError(throwable -> Log.i("Assist", "doOnComplete"));
    }

    @Run(name = "doOnTerminate \n注册一个动作，当它产生的Observable终止之前会被调用，无论是正常还是异常终止。")
    public void doOnTerminate() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5).
                doOnTerminate(() -> Log.i("Assist", "doOnTerminate"));
    }

    @Run(name = "doFinally \n注册一个动作，当它产生的Observable终止之后会被调用，无论是正常还是异常终止。")
    public void doFinally() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5).
                doFinally(() -> Log.i("Assist", "doOnTerminate"));
    }

    @Run(name = "toList \n Observable将多项数据组合成一个List")
    public void toList() {
        Disposable disposable = Observable.just(1, 2, 3, 4, 5).
                toList().subscribe(integers -> {
            Log.i("Assist", integers + "");
        });
    }

}
