package com.codebag.code.mycode.pattern.behavioral.strategy;

import android.util.Log;

public class StrategyA implements Strategy {

    @Override
    public void show() {
        Log.i("peter", "strategy a");
    }

}
