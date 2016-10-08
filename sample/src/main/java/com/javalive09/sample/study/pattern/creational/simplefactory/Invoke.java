package com.javalive09.sample.study.pattern.creational.simplefactory;

import com.javalive09.codebag.Entry;

public class Invoke extends Entry {

    public void invoke() {
        SimpleFactory.getCar("bmw").drive();
    }

}
