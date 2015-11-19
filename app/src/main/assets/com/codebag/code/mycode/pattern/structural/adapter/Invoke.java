package com.codebag.code.mycode.pattern.structural.adapter;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 更好的连接不兼容的接口
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends MyCode {

    public Invoke(InovkedViewActivity act) {
        super(act);
    }
    
    @Entry
    public void invoke() {
        new Adapter().doSomething();
    }

}
