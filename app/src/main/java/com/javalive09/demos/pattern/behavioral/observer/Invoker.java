package com.javalive09.demos.pattern.behavioral.observer;

/**
 * 一个对象状态的改变能影响一系列对象状态的改变
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker {

    public void invoke() {
        Obserable obserable = new Obserable();
        Observer a = new ObserverA();
        Observer b = new ObserverB();
        obserable.addObserver(a);
        obserable.addObserver(b);
        obserable.actionAll();
    }

}
