package com.codebag.code.mycode.pattern.structural.proxy;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 控制对一个对象的访问
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker extends MyCode {

    public Invoker(InovkedViewActivity act) {
        super(act);
    }

    @Entry
    public void invoke() {
        new Proxy().show();
    }
    
}
