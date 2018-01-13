package com.javalive09.demos.rxjava;

import com.javalive09.codebag.PlayerActivity;
import com.javalive09.codebag.Play;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;


/**
 * 辅助操作
 * <p>
 * 文档：https://mcxiaoke.gitbooks.io/rxdocs/content/Subject.html
 */

public class Assist {

    /**
     * 延迟执行
     */
    @Play
    public void delay() {
        Observable.just(1, 2, 3, 4, 5).delay(1000, TimeUnit.MILLISECONDS).subscribe(s -> PlayerActivity.context().showText(s + ""));
    }

    /**
     * 切换线程（切换接收事件线程）
     */
    @Play
    public void observerOn() {
        Observable.just(1, 2, 3, 4, 5).observeOn(Schedulers.newThread()).subscribe(s -> PlayerActivity.context().showText(s + ""));
    }

    /**
     * ?
     */
    @Play
    public void serialize() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).serialize().subscribe(s -> PlayerActivity.context().showText(s + ""));
    }

    /**
     * 切换线程（切换发出事件线程）
     */
    @Play
    public void subscribeOn() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).subscribeOn(Schedulers.computation()).subscribe(s -> PlayerActivity.context().showText(s + ""));
    }

    /**
     *
     */
    @Play
    public void timeInterval() {
        Observable.just(1, 3, 2, 4, 5, 8, 6, 7).timeInterval().subscribe(s -> PlayerActivity.context().showText(s + ""));
    }

    /**
     *
     */
    @Play
    public void timeout() {
        Observable.just(1, 2, 3, 4, 5).delay(1000, TimeUnit.MILLISECONDS).timeout(500, TimeUnit.MILLISECONDS)
                .subscribe(s -> PlayerActivity.context().showText(s + ""), throwable -> PlayerActivity.context().showText(throwable.toString()));
    }

    /**
     *
     */
    @Play
    public void timestamp() {
        Observable.just(1, 2, 3, 4, 5).timestamp().subscribe(s -> PlayerActivity.context().showText(s + ""));
    }

}
