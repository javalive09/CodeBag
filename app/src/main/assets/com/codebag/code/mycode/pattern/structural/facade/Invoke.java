package com.codebag.code.mycode.pattern.structural.facade;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 为复杂系统提供一个简单的接口
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
        Facade f = new Facade();
        f.actionA();
        f.actionB();
        f.actionC();
    }

}
