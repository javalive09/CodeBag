package com.javalive09.demos.rxjava;

import android.util.Log;

import com.javalive09.codebag.CaseActivity;
import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 条件
 */

@Player(name = "Condition 条件")
public class Condition {

    @Play(name = "all \n判定是否Observable发射的所有数据都满足某个条件")
    public void all() {
        Observable.just(1, 2, 3)
                .all(integer -> {
                    return integer < 4;    //判断是不是发射的所有数据都小于3
                }).subscribe(aBoolean -> Log.i("condition", "boolean = " + aBoolean));

        Observable.just(1, 2, 3, 4, 5)
                .all(integer -> {
                    return integer < 4;    //判断是不是发射的所有数据都小于3
                }).subscribe(aBoolean -> Log.i("condition", "boolean = " + aBoolean));
    }

    @Play(name = "amb \n给定两个或多个Observables，它只发射首先发射数据或通知的那个Observable的所有数据")
    public void amb() {
        Observable<Integer> delay3 = Observable.just(1, 2, 3).delay(3000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay2 = Observable.just(4, 5, 6).delay(2000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay1 = Observable.just(7, 8, 9).delay(1000, TimeUnit.MILLISECONDS);
        Observable.amb(Arrays.asList(delay1, delay2, delay3)).subscribe(integer -> Log.i("condition", "" + integer));
    }

    @Play(name = "contains \n 判定一个Observable是否发射一个特定的值")
    public void contains() {
        Observable.just(1, 2, 3)
                .contains(1).subscribe(aBoolean -> Log.i("condition", "" + aBoolean));
    }

    @Play(name = "DefaultIfEmpty \n发射来自原始Observable的值，如果原始Observable没有发射任何值，就发射一个默认值")
    public void defaultIfEmpty() {
        Observable.just(1, 2, 3)
                .defaultIfEmpty(4)
                .subscribe(s -> Log.i("condition", "" + s));
    }

    @Play(name = "sequenceEqual \n判定两个Observables是否发射相同的数据序列")
    public void sequenceEqual() {
        Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(aBoolean -> Log.i("condition", "" + aBoolean));
        Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2, 3))
                .subscribe(aBoolean -> Log.i("condition", " " + aBoolean));
    }

    @Play(name = "skipUntil \n丢弃原始Observable发射的数据，直到第二个Observable发射了一项数据？")
    public void skipUntil() {
        Observable.just(1, 2, 3).subscribeOn(Schedulers.newThread()).
                skipUntil(Observable.just(4, 5, 6)).
                subscribe(s -> Log.i("condition", s + ""));
    }

    @Play(name = "skipWhile \n丢弃Observable发射的数据，直到一个指定的条件不成立")
    public void skipWhile() {
        Observable.just(1, 2, 3).skipWhile(integer -> integer < 3).
                subscribe(integer -> Log.i("condition", integer + ""));
    }

    @Play(name = "takeUntil \n当第二个Observable发射了一项数据或者终止时，丢弃原始Observable发射的任何数据 ")
    public void takeUntil() {
        Observable.just(1, 2, 3).subscribeOn(Schedulers.newThread()).delay(2, TimeUnit.SECONDS).
                takeUntil(Observable.just(4, 5, 6)).
                subscribe(s -> Log.i("condition", s + ""));
    }

    @Play(name = "takeWhile \n 发射Observable发射的数据，直到一个指定的条件不成立")
    public void takeWhile() {
        Observable.just(1, 2, 3, 4, 5).takeWhile(integer -> integer < 4).
                subscribe(integer -> Log.i("condition", integer + ""));
    }

}
