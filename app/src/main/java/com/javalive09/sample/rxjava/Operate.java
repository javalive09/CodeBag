package com.javalive09.sample.rxjava;

import android.text.TextUtils;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.util.Arrays;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by peter on 2017/4/28.
 */

public class Operate extends Entry {

    public void buffer() {
        Observable.from(Arrays.asList("a", "b", "c", "d", "e", "f")).
                buffer(2).
                subscribe(lists -> Log.i("buffer" + lists.toString()));
    }

    /**
     * 解决嵌套回调（callback hell）问题
     * 一个接口的请求依赖另一个API请求返回的数据
     */
    public void flatmap() {
        Observable.from(Arrays.asList("a", "b", "c", "d", "e", "f")).
                flatMap(s -> Observable.just("flatmap-" + s)).
                subscribe(s -> Log.i("flafmap=" + s));
    }

    public void groupBy() {
        Observable.from(Arrays.asList("a", "b", "a", "d", "e", "f")).
                groupBy(s -> {
                    int key = 4;
                    if (TextUtils.equals("a", s)) {
                        key = 1;
                    } else if (TextUtils.equals("b", s)) {
                        key = 2;
                    } else if (TextUtils.equals("c", s)) {
                        key = 3;
                    } else if (TextUtils.equals("d", s)) {
                        key = 4;
                    } else if (TextUtils.equals("e", s)) {
                        key = 5;
                    }
                    return key;
                }).subscribe(integerStringGroupedObservable -> {
            int key = integerStringGroupedObservable.getKey();
            Log.i(integerStringGroupedObservable.toString());
            switch (key) {
                case 1:
                    integerStringGroupedObservable.subscribe(s -> Log.i(key + " " + s));
                    break;
                case 2:
                    integerStringGroupedObservable.subscribe(s -> Log.i(key + " " + s));
                    break;
                case 3:
                    integerStringGroupedObservable.subscribe(s -> Log.i(key + " " + s));
                    break;
                case 4:
                    integerStringGroupedObservable.subscribe(s -> Log.i(key + " " + s));
                    break;
                case 5:
                    integerStringGroupedObservable.subscribe(s -> Log.i(key + " " + s));
                    break;

            }
        });

    }

    public void map() {
        Observable.just("emit").map(s -> "map:" + s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> showTxt(s));
    }

    public void scan() {
        Observable.from(Arrays.asList("a", "b", "c", "d", "e", "f"))
                .scan((s, s2) -> s + s2)
                .subscribe(s -> Log.i(s));
    }

    public void window() {
        Observable.from(Arrays.asList("a", "b", "c", "d", "e", "f"))
                .window(2)
                .subscribe(stringObservable -> {
                    Log.i(stringObservable.toString());
                    stringObservable.subscribe(s -> Log.i(s));
                });
    }


}
