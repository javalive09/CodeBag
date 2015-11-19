package com.codebag.code.mycode.pattern.structural.adapter;

public class Adapter {
    
    Adaptee mAdaptee;
    
    public Adapter() {
        mAdaptee = new Adaptee();
    }
    
    public void doSomething() {
        mAdaptee.doOtherthing();
    }
}
