package com.codebag.code.mycode.pattern.behavioral.state;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 动态的控制一个对象状态的切换。
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
        StateController controller = new StateController();
        controller.setCurrentState(StateController.A);
        controller.action();
    }

}
