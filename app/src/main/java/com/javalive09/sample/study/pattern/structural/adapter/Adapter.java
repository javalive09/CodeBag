package com.javalive09.sample.study.pattern.structural.adapter;

public class Adapter {
    
    Adaptee mAdaptee;
    
    public Adapter() {
        mAdaptee = new Adaptee();
    }
    
    public void doSomething() {
        mAdaptee.doOtherthing();
    }
}
