package com.javalive09.demos.rxjava;

import android.text.TextUtils;
import android.util.Log;

import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;

import io.reactivex.Observable;

@Player(name = "数学和聚合操作")
public class Math {

    @Play(name = "concat \n不交错的发射两个或多个Observable的发射物  \nMerge操作符也差不多，它结合两个或多个Observable的发射物，但是数据可能交错，而Concat不会让多个Observable的发射物交错")
    public void concat() {
        Observable.concat(Observable.just(1, 2, 3, 4, 5, 6, 7, 8),
                Observable.just(9, 10, 11, 12, 13, 14, 15))
                .subscribe(i -> Log.i("Math", "" + i));

        //依次检查memory、disk和network中是否存在数据，任何一步一旦发现数据后面的操作都不执行。
        Observable<String> memory = Observable.create(subscriber -> {
            String memoryCache = null;
            if (memoryCache != null) {
                subscriber.onNext(memoryCache);
            } else {
                subscriber.onComplete();
            }
        });
        Observable<String> disk = Observable.create(subscriber -> {
            String cachePref = "";
            if (!TextUtils.isEmpty(cachePref)) {
                subscriber.onNext(cachePref);
            } else {
                subscriber.onComplete();
            }
        });
        Observable<String> network = Observable.just("network");
        Observable.concat(memory, disk, network).first("").subscribe(s -> Log.i("Math", s));
    }

    @Play(name = "count \n计算原始Observable发射物的数量，然后只发射这个值   发射个数")
    public void count() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).
                count().subscribe(s -> Log.i("Math", "" + s));
    }

    @Play(name = "reduce \n按顺序对Observable发射的每项数据应用一个函数并发射最终的值 这种操作有时被称为累积，聚集，压缩，折叠，注射等")
    public void reduce() {
        Observable.just(1, 2, 3, 4, 5, 6, 7).
                reduce((integer, integer2) -> {
                    Log.i("Math", "integer=" + integer + ";integer2 = " + integer2);
                    return integer + integer2;

                }).subscribe(s -> Log.i("Math", "" + s));
    }

}
