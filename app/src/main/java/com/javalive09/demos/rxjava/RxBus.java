package com.javalive09.demos.rxjava;


import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {

    private final Subject<Object> bus;

    private static class HOLDER {
        private static final RxBus BUS = new RxBus();
    }

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance(){
        return HOLDER.BUS;
    }

    public void post(Object object) {
        bus.onNext(object);
    }

}
