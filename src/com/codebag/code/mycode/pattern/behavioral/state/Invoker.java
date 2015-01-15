package com.codebag.code.mycode.pattern.behavioral.state;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 动态的控制一个对象状态的切换。
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
        StateController controller = new StateController();
        controller.setCurrentState(StateController.A);
        controller.action();
    }

}
