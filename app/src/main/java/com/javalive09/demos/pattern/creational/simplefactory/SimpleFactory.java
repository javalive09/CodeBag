package com.javalive09.demos.pattern.creational.simplefactory;

public class SimpleFactory {
    
    public static Car getCar(String type) {
        if("benz".equalsIgnoreCase(type)) {
            return new BENZ();
        }else if("bmw".equalsIgnoreCase(type)) {
            return new BMW();
        }
        return null;
    }
    
}
