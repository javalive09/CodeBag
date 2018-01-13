package com.javalive09.demos.rxjava;

import com.javalive09.codebag.PlayerActivity;
import com.javalive09.codebag.Play;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 组合
 */

public class Composite {

    /**
     * 注册的时候所有输入信息（邮箱、密码、电话号码等）合法才点亮注册按钮
     */
    @Play
    public void combineLatest() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        list.add(Observable.fromArray("a", "b", "c", "d", "e"));
        list.add(Observable.fromArray("1", "2", "3", "4", "5"));

        Observable.combineLatest(list, args -> {
            StringBuilder s = new StringBuilder();
            for (Object o : args) {
                s.append(o);
            }
            return s.toString();
        }).subscribe(s -> PlayerActivity.context().showText(s));
    }

    public void join() {

    }

    /**
     * 一组数据来自网络，一组数据来自文件，需要合并两组数据一起展示。
     * 界面需要等到多个接口并发取完数据，再更新
     */
    @Play
    public void merge() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        list.add(Observable.fromArray("a", "b", "c", "d", "e"));
        list.add(Observable.fromArray("1", "2", "3", "4", "5"));
        Observable.merge(list, 5).subscribe(s -> PlayerActivity.context().showText(s));
    }

    @Play
    public void merge_multi_thread() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        Observable a = Observable.create(subscriber -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("a");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.newThread());

        Observable b = Observable.create(subscriber -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("b");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.newThread());

        Observable c = Observable.create(subscriber -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("c");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.newThread());
        list.add(a);
        list.add(b);
        list.add(c);
        long start = System.currentTimeMillis();
        Observable.merge(list).subscribe(s -> PlayerActivity.context().showText((System.currentTimeMillis() - start) + " - " + s));

    }

    @Play
    public void startWith() {
        Observable.fromArray("1", "2", "3", "4", "5")
                .startWith(Arrays.asList("a", "b", "c", "d", "e"))
                .subscribe(s -> PlayerActivity.context().showText(s));
    }

    public void switch_() {

    }

    @Play
    public void zip() {
        List<Observable<String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Observable<String> observable = Observable.just("count = " + i);
            list.add(observable);
        }

        Observable.zip(list, args -> {
            StringBuilder result = new StringBuilder();
            for (Object o : args) {
                result.append(o);
            }
            PlayerActivity.context().addText("peter=====");
            return result.toString();
        }).subscribe(s -> {
            PlayerActivity.context().addText("result =" + s);
            PlayerActivity.context().showAddedText();
        });
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
            subscriber.onComplete();
        }).subscribeOn(Schedulers.newThread());

        Observable b = Observable.create(subscriber -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("b");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.newThread());

        Observable c = Observable.create(subscriber -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscriber.onNext("c");
            subscriber.onComplete();
        }).subscribeOn(Schedulers.newThread());

        list.add(a);
        list.add(b);
        list.add(c);
        long start = System.currentTimeMillis();
        Observable.zip(list, args -> args.toString()).
                subscribe(s -> PlayerActivity.context().showText(((System.currentTimeMillis() - start) + " - " + s)));

    }

}
