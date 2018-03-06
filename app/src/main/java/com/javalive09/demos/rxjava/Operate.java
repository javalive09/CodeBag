package com.javalive09.demos.rxjava;

import android.text.TextUtils;
import android.util.Log;

import com.javalive09.annotation.Test;
import com.javalive09.annotation.Tester;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Tester(name = "变换操作")
public class Operate {

    @Test(name = "buffer \n定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值")
    public void buffer() {
        Observable.fromArray("a", "b", "c", "d", "e", "f").
                buffer(2).
                subscribe(lists -> Log.i("Operate", "buffer" + lists.toString()));
    }

    @Test(name = "flatmap \n 映射变换成另一个Observable对象传递下去, 多次变换，平铺开 解决嵌套回调（callback hell）问题")
    public void flatmap() {
        Observable.fromArray("a", "b", "c", "d", "e", "f").
                flatMap(s -> Observable.just("flatmap-" + s)).
                subscribe(s -> Log.i("Operate", "flafmap=" + s));
    }

    @Test(name = "groupBy \n 将一个Observable分拆为一些Observables集合，它们中的每一个发射原始Observable的一个子序列")
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
            Log.i("Operate", integerStringGroupedObservable.toString());
            switch (key) {
                case 1:
                    integerStringGroupedObservable.subscribe(s -> Log.i("Operate", key + " " + s));
                    break;
                case 2:
                    integerStringGroupedObservable.subscribe(s -> Log.i("Operate", key + " " + s));
                    break;
                case 3:
                    integerStringGroupedObservable.subscribe(s -> Log.i("Operate", key + " " + s));
                    break;
                case 4:
                    integerStringGroupedObservable.subscribe(s -> Log.i("Operate", key + " " + s));
                    break;
                case 5:
                    integerStringGroupedObservable.subscribe(s -> Log.i("Operate", key + " " + s));
                    break;

            }
        });

    }

    @Test(name = "map \n 映射变换成另一个对象传递下去，一次变换映射")
    public void map() {
        Observable.just("emit").map(s -> "map:" + s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.i("Operate", s));
    }

    @Test(name = "scan \n 连续地对数据序列的每一项应用一个函数，然后连续发射结果")
    public void scan() {
        Observable.fromArray("a", "b", "c", "d", "e", "f")
                .scan((s, s2) -> {
                    Log.i("Operate", "s=" + s + ";s2=" + s2);
                    return s + s2;
                })
                .subscribe(s -> Log.i("Operate", s));
    }

    @Test(name = "window \n window操作符会在时间间隔内缓存结果，类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable")
    public void window() {
        Observable.fromArray("a", "b", "c", "d", "e", "f")
                .window(2)
                .subscribe(stringObservable -> {
                    Log.i("Operate", stringObservable.toString());
                    stringObservable.subscribe(s -> Log.i("Operate", s));
                });
    }


}
