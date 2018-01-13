package com.javalive09.demos.pattern.structural.adapter;


/**
 * 更好的连接不兼容的接口
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke {

    public void invoke() {
        new Adapter().doSomething();
    }

}
