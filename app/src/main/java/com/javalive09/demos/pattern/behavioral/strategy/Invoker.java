package com.javalive09.demos.pattern.behavioral.strategy;


/**
 * 把一个对象的算法，行为抽象出来，使他们能够互相替换。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker {

    public void invoke() {
        Actor a = new Actor();
        a.setStrategy(new StrategyA());
        a.show();
    }

}
