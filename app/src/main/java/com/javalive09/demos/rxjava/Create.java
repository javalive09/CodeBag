package com.javalive09.demos.rxjava;

import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.javalive09.annotation.Run;
import com.javalive09.annotation.Code;
import com.javalive09.codebag.CodeActivity;

import org.reactivestreams.Publisher;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.schedulers.Schedulers;

/**
 * 创建
 */

@Code(name = "Create 创建")
public class Create {

    @Run
    public void create(CodeActivity codeActivity) {
        Disposable disposable = new ObservableCreate<>(e -> {
            e.onNext("a");
            e.onNext("b");
            e.onNext("c");
            e.onNext("d");
            e.onNext("e");
            e.onComplete();
        }).subscribe(o -> Log.i("create", o + ""));

        Disposable disposable1 = Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onNext("e");
            subscriber.onComplete();
        }).subscribe(t -> {
            Log.i("create", t + "");
        });
    }

    @Run(name = "Just \n将单个数据转换为发射那个数据的Observable")
    public void just(CodeActivity codeActivity) {
        Disposable disposable = Observable.just("a").subscribe(s -> Log.i("create", s + ""));
    }

    @Run(name = "defer\n 直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable")
    public void defer(CodeActivity codeActivity) {
        Disposable disposable = Observable.defer(() -> Observable.just(System.currentTimeMillis())).subscribe(s -> Log.i("Create", "defer = " + s));
    }

    @Run(name = "fromArray \n 将数组数据类型转换为Observable")
    public void fromArray(CodeActivity codeActivity) {
        Disposable disposable = Observable.fromArray("a", "b", "c", "d", "e").subscribe(s -> Log.i("Create", "from = " + s));
    }

    @Run(name = "interval \n 创建一个按固定时间间隔发射整数序列的Observable")
    public void interval(CodeActivity codeActivity) {
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.i("Create", "interval = " + aLong));
        Disposable disposable1 = Observable.just("").delay(10, TimeUnit.SECONDS).subscribe(s -> disposable.dispose());

        Disposable disposable2 = Observable.interval(2, 3, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.i("peter", "interval delay"));
    }

    @Run(name = "range \n 创建一个发射特定整数序列的Observable")
    public void range(CodeActivity codeActivity) {
        Disposable disposable = Observable.range(3, 5).subscribe(integer -> Log.i("Create", "range = " + integer));
    }

    @Run(name = "timer \n 创建一个Observable，它在一个给定的延迟后发射一个特殊的值")
    public void timer(CodeActivity codeActivity) {
        Log.i("peter", "repeat thread1 = " + Thread.currentThread().getName());
        Disposable disposable = Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            Log.i("peter", "timer1 start = " + aLong);
            Log.i("peter", "timer1 repeat thread2 = " + Thread.currentThread().getName());
        });

        Disposable disposable1 = Observable.timer(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            Log.i("peter", "timer2 start = " + aLong);
            Log.i("peter", "timer2 repeat thread2 = " + Thread.currentThread().getName());
        });
    }

    @Run
    public void repeat(CodeActivity codeActivity) {
        Disposable disposable = Observable.just("").repeat(3)
                .subscribeOn(Schedulers.newThread()).subscribe(s -> {
                    SystemClock.sleep(3_000);
            Log.i("peter", "repeat thread = " + Thread.currentThread().getName());
        });

        codeActivity.showText("abc");
    }

    @Run
    public void repeatWhen(CodeActivity codeActivity) {
        Disposable disposable = Observable.just(1L).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
                return objectObservable.delay(2, TimeUnit.SECONDS);
            }
        }).filter(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Exception {
                Log.i("peter", "repeat along =" + aLong);

                if(aLong <= 3) {
                    return true;
                }
                return false;
            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                Log.i("peter", "repeatwhen thread = " + Thread.currentThread().getName());
            }
        });
    }

    @Run
    public void fromCallable(CodeActivity codeActivity) {
        new CompositeDisposable().add(
        Observable.fromCallable(new Callable<String>() {

            @Override
            public String call() throws Exception {
                SystemClock.sleep(5000);
                return "abc";
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                codeActivity.showText(s);
            }
        }));
        codeActivity.showText("loading...");
    }

    @Run
    public void intervalAndCount(CodeActivity codeActivity) {
        Disposable disposable = Observable.interval(3, TimeUnit.MILLISECONDS).count().filter(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Exception {
                Log.i("peter", "repeat thread = along =" + aLong);
                if(aLong <= 3) {
                   return true;
                }
                return false;
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribe(aLong -> Log.i("peter", "repeat thread = " + Thread.currentThread().getName()));
        codeActivity.showText("abc");
    }

    @Run
    public void repeatDelay(CodeActivity codeActivity) {
        Disposable disposable = repeat(5, 5000, new RepeatConsumer<Long>(5000) {
            @Override
            public void run(Long l) {
                Log.i("peter", "repeat thread = " + Thread.currentThread().getName() + "; l=" + l);
            }
        });
        TextView textView = new TextView(codeActivity);
        textView.setText("123");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disposable.dispose();
            }
        });
        codeActivity.setContentView(textView);
    }

    public static Disposable repeat(int times, long delay, RepeatConsumer<Long> repeatConsumer) {
        return Single.just(1L).repeat(times).delay(delay, TimeUnit.MILLISECONDS).subscribe(repeatConsumer);
    }

    public static Disposable repeat2(int times, long delay, long period, Retry<Long> retry) {
        return Observable.interval(delay, period, TimeUnit.MILLISECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                if(retry != null) {
                    retry.accept(aLong);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });
    }

    public static abstract class RepeatConsumer<T> implements Consumer<T> {

        private long period;

        public RepeatConsumer(long period) {
            this.period = period;
        }

        @Override
        public void accept(T t) throws Exception {
            run(t);
            SystemClock.sleep(period);
        }

        public abstract void run(T t);
    }


    public static abstract class Retry<T> implements Consumer<T> {

        private int count;

        private int currentCount;

        public Retry(int count) {
            this.count = count;
        }

        @Override
        public void accept(T t) throws Exception {
            currentCount ++;
            if(currentCount > count) {
                onFail();
                throw new Exception("time out!");
            }else {
                onTry(t);
            }
        }

        public abstract void onTry(T t);

        public abstract void onFail();
    }


    @Run
    public void repeatDelay2(CodeActivity codeActivity) {
        repeat2(3, 1000, 2000, new Retry<Long>(3) {

            @Override
            public void onTry(Long aLong) {
                Log.i("peter", "retry thread = " + Thread.currentThread().getName() + "; aLong=" + aLong);
            }

            @Override
            public void onFail() {
                Log.i("peter", "retry fail = " + Thread.currentThread().getName() );
            }
        });
    }


}
