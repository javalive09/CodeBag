package com.codebag.code.mycode.pattern.structural.proxy;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 控制对一个对象的访问
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
        new Proxy().show();
    }
    
}
