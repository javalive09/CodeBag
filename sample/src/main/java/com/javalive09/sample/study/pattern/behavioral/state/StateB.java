package com.javalive09.sample.study.pattern.behavioral.state;

import android.util.Log;

public class StateB implements State {

    @Override
    public void action() {
        Log.i("peter", "stateB action");
    }

}
