package com.javalive09.sample.study.pattern.creational.protoype;

public class CloneObj implements Cloneable{
    
    public Object clone() {
        CloneObj obj = null;
        try {
            obj = (CloneObj) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
        return obj;
    }
    
}
