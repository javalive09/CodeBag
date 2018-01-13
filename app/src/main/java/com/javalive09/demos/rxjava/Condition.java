package com.javalive09.demos.rxjava;

import android.util.Log;

import com.javalive09.codebag.PlayerActivity;
import com.javalive09.codebag.Play;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 条件
 */

public class Condition {

    @Play
    public void all() {
        Observable.just(1, 2, 3)
                .all(integer -> {
                    PlayerActivity.context().addText(integer + "");
                    return integer < 4;    //判断是不是发射的所有数据都小于3
                }).subscribe(aBoolean -> Log.i("condition", "boolean = " + aBoolean));

        Observable.just(1, 2, 3, 4, 5)
                .all(integer -> {
                    Log.i("condition", "" + integer);
                    return integer < 4;    //判断是不是发射的所有数据都小于3
                }).subscribe(aBoolean -> Log.i("condition", "boolean = " + aBoolean));
    }

    @Play
    public void amb() {
        Observable<Integer> delay3 = Observable.just(1, 2, 3).delay(3000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay2 = Observable.just(4, 5, 6).delay(2000, TimeUnit.MILLISECONDS);
        Observable<Integer> delay1 = Observable.just(7, 8, 9).delay(1000, TimeUnit.MILLISECONDS);
        Observable.amb(Arrays.asList(delay1, delay2, delay3)).subscribe(integer -> Log.i("amb", "" + integer));
    }

    @Play
    public void contains() {
        Observable.just(1, 2, 3)
                .contains(1).subscribe(aBoolean -> Log.i("contains","" + aBoolean));
    }

    @Play
    public void defaultIfEmpty() {
        Observable.just(1, 2, 3)
                .defaultIfEmpty(4)
                .subscribe(s -> Log.i("defaultIfEmpty","" + s));
    }

    @Play
    public void sequenceEqual() {
        Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(aBoolean -> Log.i("sequenceEqual", "" + aBoolean));
        Observable.sequenceEqual(Observable.just(1, 2, 3), Observable.just(1, 2, 3))
                .subscribe(aBoolean -> Log.i("sequenceEqual"," " + aBoolean));
    }

    @Play
    public void skipUntil() {
        Observable.just(1, 2, 3).subscribeOn(Schedulers.newThread()).skipUntil(Observable.just(4, 5, 6)).subscribe(s -> Log.i("skipUntil",s + ""));
    }

    @Play
    public void takeUntil() {
        Observable.just(1, 2, 3).subscribeOn(Schedulers.newThread()).takeUntil(Observable.just(4, 5, 6)).subscribe(s -> Log.i("takeUntil",s + ""));
    }

    @Play
    public void takeWhile() {
        Observable.just(1, 2, 3, 4, 5).takeWhile(integer -> integer < 4).subscribe(integer -> Log.i("takeWhile",integer + ""));
    }

}
