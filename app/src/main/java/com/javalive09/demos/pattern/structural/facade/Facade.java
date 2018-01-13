package com.javalive09.demos.pattern.structural.facade;

public class Facade {
    
    private SubSystemA mA;
    private SubSystemB mB;
    private SubSystemC mC;
    
    public Facade() {
        mA = new SubSystemA();
        mB = new SubSystemB();
        mC = new SubSystemC();
    }
    
    public void actionA() {
        mA.actionA();
    }
    
    public void actionB() {
        mB.actionB();
    }
    
    public void actionC() {
        mC.actionC();
    }
}
