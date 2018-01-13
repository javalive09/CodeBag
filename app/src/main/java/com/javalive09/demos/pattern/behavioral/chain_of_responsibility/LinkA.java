package com.javalive09.demos.pattern.behavioral.chain_of_responsibility;

import android.util.Log;

public class LinkA extends Link {

    @Override
    public void action() {
        super.action();
        Log.i("peter", "action link a");
    }

}
