package com.javalive09.sample.study.pattern.behavioral.state;

import android.util.Log;

public class StateA implements State {

    @Override
    public void action() {
        Log.i("peter", "stateA action");
    }

}
