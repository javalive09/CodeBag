package com.javalive09.demos.rxjava;

import android.util.Log;

import com.javalive09.codebag.CaseActivity;
import com.javalive09.codebag.Play;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * 辅助操作
 * <p>
 * 文档：https://mcxiaoke.gitbooks.io/rxdocs/content/Subject.html
 */

public class Assist {

    @Play(name = "delay 延迟执行")
    public void delay() {
        Observable.just(1, 2, 3, 4, 5).delay(1000, TimeUnit.MILLISECONDS).subscribe(s -> Log.i("Assist", s + ""));
    }

    @Play(name = "切换线程（切换接收事件线程）")
    public void observerOn() {
        Observable.just(1, 2, 3, 4, 5).observeOn(Schedulers.newThread()).subscribe(s -> Log.i("Assist", s + ""));
    }

    /**
     * http://sherlockshi.github.io/2016/10/17/15_Android/1560_RxJava/RxJava%E6%93%8D%E4%BD%9C%E7%AC%A6%E5%AE%9E%E8%B7%B5%EF%BC%9A6_%E8%BE%85%E5%8A%A9%E6%93%8D%E4%BD%9C%E4%B9%8B4_serialize/
     */
    @Play(name = "serialize \n一个Observable可以异步调用它的观察者的方法，可能是从不同的线程调用。这可能会让Observable行为不正确，它可能会在某一个onNext调用之前尝试调用onCompleted或onError方法，或者从两个不同的线程同时调用onNext方法。使用Serialize操作符，你可以纠正这个Observable的行为，保证它的行为是正确的且是同步的")
    public void serialize() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).serialize().subscribe(s -> Log.i("Assist", s + ""));
    }

    @Play(name = "切换线程（切换发出事件线程）")
    public void subscribeOn() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).subscribeOn(Schedulers.computation()).subscribe(s -> Log.i("Assist", s + ""));
    }

    /**
     *
     */
    @Play(name = "timeInterval \n这个操作符将原始Observable转换为另一个Observable，后者发射一个标志替换前者的数据项，这个标志表示前者的两个连续发射物之间流逝的时间长度。")
    public void timeInterval() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).timeInterval().subscribe(s -> Log.i("Assist", s + ""));
    }

    /**
     *
     */
    @Play(name = "timeout 对原始Observable的一个镜像，如果过了一个指定的时长仍没有发射数据，它会发一个错误通知")
    public void timeout() {
        Observable.just(1, 2, 3, 4, 5).delay(1000, TimeUnit.MILLISECONDS).timeout(500, TimeUnit.MILLISECONDS)
                .subscribe(s -> Log.i("Assist", s + ""), throwable -> Log.i("Assist", throwable.toString()));
    }

    /**
     *
     */
    @Play(name = "timestamp 给Observable发射的数据项附加一个时间戳")
    public void timestamp() {
        Observable.just(1, 2, 3, 4, 5).timestamp().subscribe(s -> Log.i("Assist", s + ""));
    }

}
