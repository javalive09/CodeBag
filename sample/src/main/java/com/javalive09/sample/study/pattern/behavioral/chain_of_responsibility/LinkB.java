package com.javalive09.sample.study.pattern.behavioral.chain_of_responsibility;

import android.util.Log;

public class LinkB extends Link {

    @Override
    public void action() {
        super.action();
        Log.i("peter", "action link b");
    }

}
