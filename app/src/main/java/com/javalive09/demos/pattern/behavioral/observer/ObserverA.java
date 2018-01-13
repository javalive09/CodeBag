package com.javalive09.demos.pattern.behavioral.observer;

import android.util.Log;

public class ObserverA implements Observer {

    @Override
    public void action() {
        Log.i("peter", "A action");
    }

}
