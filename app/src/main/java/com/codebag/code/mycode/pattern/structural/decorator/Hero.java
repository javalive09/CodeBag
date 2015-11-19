package com.codebag.code.mycode.pattern.structural.decorator;

import android.util.Log;

public class Hero implements Attacker{
    
    @Override
    public void attack() {
        Log.i("peter", "hero attack");
    }
    
}
