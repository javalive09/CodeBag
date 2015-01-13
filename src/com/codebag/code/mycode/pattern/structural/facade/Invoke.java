package com.codebag.code.mycode.pattern.structural.facade;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 为复杂系统提供一个简单的接口
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends MyCode {

    public Invoke(MainActivity act) {
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
