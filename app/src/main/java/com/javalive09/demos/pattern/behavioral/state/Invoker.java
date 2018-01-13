package com.javalive09.demos.pattern.behavioral.state;

/**
 * 动态的控制一个对象状态的切换。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker {

    public void invoke() {
        StateController controller = new StateController();
        controller.setCurrentState(StateController.A);
        controller.action();
    }

}
