package com.javalive09.sample.study.pattern.behavioral.chain_of_responsibility;

import com.javalive09.codebag.Entry;

/**
 * 使多个对象都有机会处理消息。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker extends Entry {

    public void invoke() {
        Link a = new LinkA();
        Link a1 = new LinkA();
        Link b = new LinkB();
        Link b1 = new LinkB();
        a.setNext(a1);
        a1.setNext(b);
        b.setNext(b1);
        a.action();
    }
    
}
