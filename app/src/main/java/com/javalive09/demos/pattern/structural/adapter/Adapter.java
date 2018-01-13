package com.javalive09.demos.pattern.structural.adapter;

public class Adapter {
    
    Adaptee mAdaptee;
    
    public Adapter() {
        mAdaptee = new Adaptee();
    }
    
    public void doSomething() {
        mAdaptee.doOtherthing();
    }
}
