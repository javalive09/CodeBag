package com.javalive09.sample.study.pattern.behavioral.observer;

import android.util.Log;

public class ObserverB implements Observer {

    @Override
    public void action() {
        Log.i("peter", "action B");
    }

}
