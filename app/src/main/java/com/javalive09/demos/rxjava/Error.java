package com.javalive09.demos.rxjava;


import android.util.Log;

import com.javalive09.codebag.Test;
import com.javalive09.codebag.Tester;

import io.reactivex.Observable;

@Tester(name = "错误处理")
public class Error {

    @Test
    public void retry() {
        Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onError(new Exception("xxxxx"));
            subscriber.onNext("e");
            subscriber.onComplete();
        }).retry(3).subscribe(t -> Log.i("Error", "t=" + t.toString()),
                throwable -> Log.i("Error", "throwable=" + throwable.getMessage()));
    }

    @Test(name = "retryWhen \n和retry类似，区别是，retryWhen将onError中的Throwable传递给一个函数，这个函数产生另一个Observable，retryWhen观察它的结果再决定是不是要重新订阅原始的Observable")
    public void retryWhen() {
        Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onError(new Exception("xxxxx"));
            subscriber.onNext("e");
            subscriber.onComplete();
        }).retryWhen(throwableObservable -> observer -> Log.i("Error", "observer=" + observer)).
                subscribe(t -> Log.i("Error", "time = " + t),
                        throwable -> Log.i("Error", "throwable=" + throwable.getMessage()));
    }


    @Test(name = "onErrorReturn \n让Observable遇到错误时发射一个特殊的项并且正常终止")
    public void onErrorReturn() {
        Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onError(new Exception("xxxxxx"));
            subscriber.onNext("e");
            subscriber.onComplete();
        }).onErrorReturn(throwable -> Log.i("Error", throwable.getMessage())).subscribe(o -> Log.i("Error", "o = " + o),
                throwable -> Log.i("Error", "throwable=" + throwable.getMessage()));
    }

    @Test(name = "onErrorResumeNext \n让Observable在遇到错误时开始发射第二个Observable的数据序列")
    public void onErrorResumeNext() {
        Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onError(new Exception("xxxxxx"));
            subscriber.onNext("e");
            subscriber.onComplete();
        }).onErrorResumeNext(throwable -> {
            Log.i("Error", throwable.toString());
        }).subscribe(o -> Log.i("Error", "o = " + o),
                throwable -> Log.i("Error", "throwable=" + throwable.getMessage()));
    }

    @Test(name = "onExceptionResumeNext \n让Observable在遇到错误时继续发射后面的数据项?")
    public void onExceptionResumeNext() {
        Observable.create(subscriber -> {
            subscriber.onNext("a");
            subscriber.onNext("b");
            subscriber.onNext("c");
            subscriber.onNext("d");
            subscriber.onError(new Exception("xxxxxx"));
            subscriber.onNext("e");
            subscriber.onComplete();
        }).onExceptionResumeNext(throwable -> Log.i("Error", "throwable1=" + throwable.toString())).
                subscribe(o -> Log.i("Error", "o = " + o),
                        throwable -> Log.i("Error", "throwable2=" + throwable.getMessage()));
    }


}
