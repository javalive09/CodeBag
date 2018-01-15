package com.javalive09.demos.rxjava;

import android.support.annotation.NonNull;
import android.util.Log;

import com.javalive09.codebag.CaseActivity;
import com.javalive09.codebag.Play;
import com.javalive09.codebag.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 组合
 */

@Player(name = "结合操作")
public class Composite {

    @Play(name = "combineLatest \n 1.所有的Observable都发射过数据。\n2.满足条件1的时候任何一个Observable发射一个数据，就将所有Observable最新发射的数据按照提供的函数组装起来发射出去。\n注册的时候所有输入信息（邮箱、密码、电话号码等）合法才点亮注册按钮")
    public void combineLatest() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        list.add(Observable.fromArray("a", "b", "c", "d", "e"));
        list.add(Observable.fromArray("1", "2", "3", "4", "5"));
        list.add(Observable.fromArray("6", "7", "8", "9", "0"));

        Observable.combineLatest(list, args -> {
            StringBuilder s = new StringBuilder();
            for (Object o : args) {
                s.append(o);
            }
            return s.toString();
        }).subscribe(s -> Log.i("Composite", s));
    }

    public void join() {

    }

    @Play(name = "merge \n合并多个Observables的发射物 \n一组数据来自网络，一组数据来自文件，需要合并两组数据一起展示。界面需要等到多个接口并发取完数据，再更新")
    public void merge() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        list.add(Observable.fromArray("a", "b", "c", "d", "e", "f"));
        list.add(Observable.fromArray("1", "2", "3", "4", "5", "6"));
        Observable.merge(list, 5).subscribe(s -> Log.i("Composite", s));
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
        Observable.merge(list).subscribe(s -> Log.i("Composite", (System.currentTimeMillis() - start) + " - " + s));

    }

    @Play(name = "startWith \n在数据序列的开头插入一条或多条指定的项")
    public void startWith() {
        Observable.fromArray("1", "2", "3", "4", "5").delay(5, TimeUnit.SECONDS)
                .startWith(Arrays.asList("a", "b", "c", "d", "e"))
                .subscribe(s -> Log.i("Composite", s));
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
            return result.toString();
        }).subscribe(s -> {
            Log.i("Composite", s);
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
                subscribe(s -> CaseActivity.showText(((System.currentTimeMillis() - start) + " - " + s)));

    }

}
