package com.javalive09.sample.rxjava;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.FuncN;
import rx.schedulers.Schedulers;

/**
 * Created by peter on 2017/5/1.
 */

public class Composite extends Entry {

    public void And_Then_When() {

    }

    /**
     * 注册的时候所有输入信息（邮箱、密码、电话号码等）合法才点亮注册按钮
     */
    public void combineLatest() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        list.add(Observable.from(Arrays.asList("a", "b", "c", "d", "e")));
        list.add(Observable.from(Arrays.asList("1", "2", "3", "4", "5")));

        Observable.combineLatest(list, args -> {
            String s = "";
            for (Object o : args) {
                s += o;
            }
            return s;
        }).subscribe(s -> Log.i(s));
    }

    public void join() {

    }

    /**
     * 一组数据来自网络，一组数据来自文件，需要合并两组数据一起展示。
     * 界面需要等到多个接口并发取完数据，再更新
     */
    public void merge() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        list.add(Observable.from(Arrays.asList("a", "b", "c", "d", "e")));
        list.add(Observable.from(Arrays.asList("1", "2", "3", "4", "5")));
        Observable.merge(list, 5).subscribe(s -> Log.i(s));
    }

    public void merge_multi_thread() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        Observable a = Observable.create(subscriber -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("a");
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread());

        Observable b = Observable.create(subscriber -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("b");
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread());

        Observable c = Observable.create(subscriber -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("c");
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread());
        list.add(a);
        list.add(b);
        list.add(c);
        long start = System.currentTimeMillis();
        Observable.merge(list).subscribe(s -> Log.i((System.currentTimeMillis() - start) + " - " + s));

    }

    public void startWith() {
        Observable.from(Arrays.asList("1", "2", "3", "4", "5"))
                .startWith(Arrays.asList("a", "b", "c", "d", "e"))
                .subscribe(s -> Log.i(s));
    }

    public void switch_() {

    }

    public void zip() {
        List<Observable<String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Observable<String> observable = Observable.just("count = " + i);
            list.add(observable);
        }

        Observable.zip(list, new FuncN<String>() {
            @Override
            public String call(Object... args) {
                return null;
            }
        });

        Observable.zip(list, args -> {
            String result = "";
            for (Object o : args) {
                result += o;
            }
            Log.e("peter=====");
            return result;
        }).subscribe(s -> Log.i("result =" + s));
    }

    public void zip_multi_thread() {
        List<Observable<String>> list = new ArrayList<>();

        Observable a = Observable.create(subscriber -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("a");
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread());

        Observable b = Observable.create(subscriber -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("b");
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread());

        Observable c = Observable.create(subscriber -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("c");
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.newThread());

        list.add(a);
        list.add(b);
        list.add(c);
        long start = System.currentTimeMillis();
        Observable.zip(list, args -> args.toString()).subscribe(s -> Log.i((System.currentTimeMillis() - start) + " - " + s));

    }

}
