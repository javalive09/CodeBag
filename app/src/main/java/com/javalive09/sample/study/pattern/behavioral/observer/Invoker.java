package com.javalive09.sample.study.pattern.behavioral.observer;

import com.javalive09.codebag.Entry;

/**
 * 一个对象状态的改变能影响一系列对象状态的改变
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker extends Entry {

    public void invoke() {
        Obserable obserable = new Obserable();
        Observer a = new ObserverA();
        Observer b = new ObserverB();
        obserable.addObserver(a);
        obserable.addObserver(b);
        obserable.actionAll();
    }

}
