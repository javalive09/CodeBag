package com.codebag.code.mycode.pattern.structural.decorator;

import android.util.Log;

public class BlueBuffer extends Buffer {
    
    public BlueBuffer(Attacker a) {
        super(a);
    }

    @Override
    public void attack() {
        super.attack();
        Log.i("peter", "blue attack!");
    }
    
}
