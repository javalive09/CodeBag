package com.javalive09.sample.rxjava;

import android.text.TextUtils;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by peter on 2017/5/3.
 */

public class Math extends Entry {

    /**
     * 顺序执行, 聚合
     */
    public void concat() {
        Observable.concat(Observable.just(1,2,3,4,5,6,7,8), Observable.just(9,10,11,12,13,14,15))
                .subscribe(i -> Log.i("" + i));

        //依次检查memory、disk和network中是否存在数据，任何一步一旦发现数据后面的操作都不执行。
        Observable<String> memory = Observable.create(subscriber -> {
            String memoryCache = null;
            if (memoryCache != null) {
                subscriber.onNext(memoryCache);
            } else {
                subscriber.onCompleted();
            }
        });
        Observable<String> disk = Observable.create(subscriber -> {
            String cachePref = "";
            if (!TextUtils.isEmpty(cachePref)) {
                subscriber.onNext(cachePref);
            } else {
                subscriber.onCompleted();
            }
        });
        Observable<String> network = Observable.just("network");
        Observable.concat(memory, disk, network).first().subscribe(s -> Log.i(s));

    }

    /**
     * 发射个数
     */
    public void count() {
        Observable.just(1,2,3,4,5,6,7,8).count().subscribe(s -> Log.i("" + s));
    }

    /**
     * 积累相加
     */
    public void reduce() {
        Observable.just(1,2,3,4,5,6,7).reduce((integer, integer2) -> integer + integer2).subscribe(s -> Log.i("" + s));
    }

}
