package com.codebag.code.mycode.pattern.behavioral.strategy;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 把一个对象的算法，行为抽象出来，使他们能够互相替换。
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
        Actor a = new Actor();
        a.setStrategy(new StrategyA());
        a.show();
    }

}
