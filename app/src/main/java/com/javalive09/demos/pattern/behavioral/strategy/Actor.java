package com.javalive09.demos.pattern.behavioral.strategy;

public class Actor {
    
    private Strategy mCurStrategy;
    
    public void setStrategy(Strategy s) {
        mCurStrategy = s;
    }
    
    public void show() {
        mCurStrategy.show();
    }
}
