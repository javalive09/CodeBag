package com.codebag.code.mycode.pattern.creational.singleton;

import android.util.Log;

public class SingleTon {
    
    private static SingleTon mInstance = null;
    
    private SingleTon() {
        
    }
    
    public static SingleTon getInstance() {
        synchronized (SingleTon.class) {
            if(mInstance == null) {
                mInstance = new SingleTon();
            }
            return mInstance;
        }
    }
    
    public void doSomething() {
        Log.i("peter", "doSomething()");
    }
    
}
