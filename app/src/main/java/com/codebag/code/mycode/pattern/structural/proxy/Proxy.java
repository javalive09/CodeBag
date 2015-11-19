package com.codebag.code.mycode.pattern.structural.proxy;

import android.util.Log;

public class Proxy implements Shower {

    private SingStar mStar;
    
    public Proxy() {
        mStar = new SingStar();
    }
    
    @Override
    public void show() {
        Log.i("peter", "before show()");
        mStar.show();
        Log.i("peter", "end show()");
    }

}
