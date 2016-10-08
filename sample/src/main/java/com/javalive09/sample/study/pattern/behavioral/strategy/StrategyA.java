package com.javalive09.sample.study.pattern.behavioral.strategy;

import android.util.Log;

public class StrategyA implements Strategy {

    @Override
    public void show() {
        Log.i("peter", "strategy a");
    }

}
