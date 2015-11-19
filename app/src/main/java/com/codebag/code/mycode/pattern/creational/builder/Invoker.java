package com.codebag.code.mycode.pattern.creational.builder;

import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 
 * 适用于一个对象由多个部分组成，各个组成部分可以灵活变化的情况。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker extends MyCode {

    public Invoker(InovkedViewActivity act) {
        super(act);
    }
    
    public void invoke() {
        Product p = new Director().build(new Builder());
    }

}
