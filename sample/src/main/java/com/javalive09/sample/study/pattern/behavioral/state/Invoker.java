package com.javalive09.sample.study.pattern.behavioral.state;

import com.javalive09.codebag.Entry;

/**
 * 动态的控制一个对象状态的切换。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker extends Entry {

    public void invoke() {
        StateController controller = new StateController();
        controller.setCurrentState(StateController.A);
        controller.action();
    }

}
