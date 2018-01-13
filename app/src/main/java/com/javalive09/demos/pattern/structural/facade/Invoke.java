package com.javalive09.demos.pattern.structural.facade;


/**
 * 为复杂系统提供一个简单的接口
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke {

    public void invoke() {
        Facade f = new Facade();
        f.actionA();
        f.actionB();
        f.actionC();
    }

}
