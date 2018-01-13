package com.javalive09.demos.rxjava;

import android.text.TextUtils;
import android.util.Log;

import com.javalive09.codebag.PlayerActivity;
import com.javalive09.codebag.Play;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 操作
 */

public class Operate {

    @Play
    public void buffer() {
        Observable.fromArray("a", "b", "c", "d", "e", "f").
                buffer(2).
                subscribe(lists -> Log.i("buffer", "buffer" + lists.toString()));
    }

    /**
     * 解决嵌套回调（callback hell）问题
     * 一个接口的请求依赖另一个API请求返回的数据
     */
    @Play
    public void flatmap() {
        Observable.fromArray("a", "b", "c", "d", "e", "f").
                flatMap(s -> Observable.just("flatmap-" + s)).
                subscribe(s -> Log.i("flatmap", "flafmap=" + s));
    }

    @Play
    public void groupBy() {
        Observable.fromArray("a", "b", "a", "d", "e", "f").
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
            Log.i("groupBy",integerStringGroupedObservable.toString());
            switch (key) {
                case 1:
                    integerStringGroupedObservable.subscribe(s -> Log.i("groupBy",key + " " + s));
                    break;
                case 2:
                    integerStringGroupedObservable.subscribe(s -> Log.i("groupBy",key + " " + s));
                    break;
                case 3:
                    integerStringGroupedObservable.subscribe(s -> Log.i("groupBy",key + " " + s));
                    break;
                case 4:
                    integerStringGroupedObservable.subscribe(s -> Log.i("groupBy",key + " " + s));
                    break;
                case 5:
                    integerStringGroupedObservable.subscribe(s -> Log.i("groupBy",key + " " + s));
                    break;

            }
        });

    }

    @Play
    public void map() {
        Observable.just("emit").map(s -> "map:" + s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> PlayerActivity.context().showText(s));
    }

    @Play
    public void scan() {
        Observable.fromArray("a", "b", "c", "d", "e", "f")
                .scan((s, s2) -> s + s2)
                .subscribe(s -> Log.i("scan",s));
    }

    @Play
    public void window() {
        Observable.fromArray("a", "b", "c", "d", "e", "f")
                .window(2)
                .subscribe(stringObservable -> {
                    Log.i("window",stringObservable.toString());
                    stringObservable.subscribe(s -> Log.i("window",s));
                });
    }


}
