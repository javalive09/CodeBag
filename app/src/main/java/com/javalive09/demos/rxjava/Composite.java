package com.javalive09.demos.rxjava;

import android.util.Log;

import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 组合
 */

@Code(name = "Composite 结合操作")
public class Composite {

    @Run(name = "combineLatest \n 1.所有的Observable都发射过数据。\n2.满足条件1的时候任何一个Observable发射一个数据，就将所有Observable最新发射的数据按照提供的函数组装起来发射出去。\n注册的时候所有输入信息（邮箱、密码、电话号码等）合法才点亮注册按钮")
    public void combineLatest() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        list.add(Observable.fromArray("a", "b", "c", "d", "e"));
        list.add(Observable.fromArray("1", "2", "3", "4", "5"));
        list.add(Observable.fromArray("6", "7", "8", "9", "0"));

        Disposable disposable = Observable.combineLatest(list, args -> {
            StringBuilder s = new StringBuilder();
            for (Object o : args) {
                s.append(o);
            }
            return s.toString();
        }).subscribe(s -> Log.i("Composite", s));
    }

    @Run(name = "join ? ")
    public void join() {
//        ArrayList<Observable<String>> list = new ArrayList<>();
//        list.add(Observable.fromArray("a", "b", "c", "d", "e", "f"));
//        list.add(Observable.fromArray("1", "2", "3", "4", "5", "6"));
    }

    @Run(name = "merge \n合并多个Observables的发射物 \n一组数据来自网络，一组数据来自文件，需要合并两组数据一起展示。界面需要等到多个接口并发取完数据，再更新")
    public void merge() {
        ArrayList<Observable<String>> list = new ArrayList<>();
        list.add(Observable.fromArray("a", "b", "c", "d", "e", "f"));
        list.add(Observable.fromArray("1", "2", "3", "4", "5", "6"));
        Disposable disposable = Observable.merge(list, 5).subscribe(s -> Log.i("Composite", s));
    }

    @Run
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
        Disposable disposable = Observable.merge(list).subscribe(s -> Log.i("Composite", (System.currentTimeMillis() - start) + " - " + s));

    }

    @Run(name = "startWith \n在数据序列的开头插入一条或多条指定的项")
    public void startWith() {
        Disposable disposable = Observable.fromArray("1", "2", "3", "4", "5").delay(5, TimeUnit.SECONDS)
                .startWith(Arrays.asList("a", "b", "c", "d", "e"))
                .subscribe(s -> Log.i("Composite", s));
    }

    @Run(name = "switchMap \n和flatMap()很像，除了一点:当源Observable发射一个新的数据项时，如果旧数据项订阅还未完成，就取消旧订阅数据和停止监视那个数据项产生的Observable,开始监视新的数据项.")
    public void switchMap() {
        Disposable disposable = Observable.just("A", "B", "C", "D", "E").switchMap(s ->
                Observable.just(s).subscribeOn(Schedulers.newThread())).observeOn(AndroidSchedulers.mainThread()).
                subscribe(s -> Log.i("Composite", s));
    }

    @Run(name = "zip \n通过一个函数将多个Observables的发射物结合到一起，基于这个函数的结果为每个结合体发射单个数据项。")
    public void zip() {
        Observable<String> observable1 = Observable.just("a", "b", "c", "d", "e").delay(5, TimeUnit.SECONDS);
        Observable<String> observable2 = Observable.just("1", "2", "3", "4", "5");

        Disposable disposable = Observable.zip(Arrays.asList(observable1, observable2), args -> {
            StringBuilder result = new StringBuilder();
            for (Object o : args) {
                result.append(o);
            }
            return result.toString();
        }).subscribe(s -> Log.i("Composite", s));
    }

    @Run()
    public void zip_multi_thread() {
        Observable<String> observable1 = Observable.just("a", "b", "c", "d", "e").subscribeOn(Schedulers.newThread()).delay(3, TimeUnit.SECONDS);
        Observable<String> observable2 = Observable.just("1", "2", "3", "4", "5").subscribeOn(Schedulers.newThread()).delay(5, TimeUnit.SECONDS);
        Observable<String> observable3 = Observable.just("6", "7", "8", "9", "10").subscribeOn(Schedulers.newThread()).delay(7, TimeUnit.SECONDS);

        long start = System.currentTimeMillis();
        Disposable disposable = Observable.zip(Arrays.asList(observable1, observable2, observable3), objects -> {
            StringBuilder result = new StringBuilder();
            for (Object o : objects) {
                result.append(o);
            }
            return result.toString();
        }).subscribe(s -> Log.i("Composite", (System.currentTimeMillis() - start) + " - " + s));

    }

}
