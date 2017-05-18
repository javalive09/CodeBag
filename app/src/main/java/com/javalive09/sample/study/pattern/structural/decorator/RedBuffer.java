package com.javalive09.sample.study.pattern.structural.decorator;

import android.util.Log;

public class RedBuffer extends Buffer {
   
    public RedBuffer(Attacker a) {
        super(a);
    }

    @Override
    public void attack() {
        super.attack();
        Log.i("peter", "red attack!");
    }
}
