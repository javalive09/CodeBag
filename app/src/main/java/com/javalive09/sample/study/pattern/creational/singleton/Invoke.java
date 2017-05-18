package com.javalive09.sample.study.pattern.creational.singleton;

import com.javalive09.codebag.Entry;

public class Invoke extends Entry {

    public void invoke() {
        SingleTon.getInstance().doSomething();
    }
}
