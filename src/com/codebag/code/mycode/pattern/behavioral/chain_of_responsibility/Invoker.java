package com.codebag.code.mycode.pattern.behavioral.chain_of_responsibility;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 使多个对象都有机会处理消息。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker extends MyCode {

    public Invoker(MainActivity act) {
        super(act);
    }

    @Entry
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
