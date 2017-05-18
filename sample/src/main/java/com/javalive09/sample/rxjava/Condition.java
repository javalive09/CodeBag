package com.javalive09.sample.rxjava;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by peter on 2017/5/2.
 */

public class Condition extends Entry {

    public void all() {
        Observable.just(1,2,3)
                .all(integer -> {
                    Log.i(""+integer);
                    return integer < 4;    //判断是不是发射的所有数据都小于3
                }).subscribe(aBoolean -> Log.i("boolean = " + aBoolean));

        Observable.just(1,2,3,4,5)
                .all(integer -> {
                    Log.i(""+integer);
                    return integer < 4;    //判断是不是发射的所有数据都小于3
                }).subscribe(aBoolean -> Log.i("boolean = " + aBoolean));
    }

    public void amb() {
        Observable<Integer> delay3 = Observable.just(1, 2, 3).delay(3000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay2 = Observable.just(4, 5, 6).delay(2000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay1 = Observable.just(7, 8, 9).delay(1000, TimeUnit.MILLISECONDS);
        Observable.amb(delay1, delay2, delay3).subscribe(integer -> Log.i("" + integer));
    }

    public void contains() {
        Observable.just(1,2,3)
                .contains(1).subscribe(aBoolean -> Log.i("" + aBoolean));
    }

    public void defaultIfEmpty() {
        Observable.just(1,2,3)
                .defaultIfEmpty(4)
                .subscribe(s -> Log.i("" + s));
    }

    public void sequenceEqual() {
        Observable.sequenceEqual(Observable.just(1,2,3), Observable.just(4,5,6))
                .subscribe(aBoolean -> Log.i(" " + aBoolean));
        Observable.sequenceEqual(Observable.just(1,2,3), Observable.just(1,2,3))
                .subscribe(aBoolean -> Log.i(" " + aBoolean));
    }

    public void skipUntil() {
        Observable.just(1,2,3).subscribeOn(Schedulers.newThread()).skipUntil(Observable.just(4,5,6)).subscribe(s -> Log.i(s + ""));
    }

    public void takeUntil() {
        Observable.just(1,2,3).subscribeOn(Schedulers.newThread()).takeUntil(Observable.just(4,5,6)).subscribe(s -> Log.i(s + ""));
    }

    public void takeWhile() {
        Observable.just(1,2,3,4,5).takeWhile(integer -> integer < 4).subscribe(integer -> Log.i(integer + ""));
    }

}
