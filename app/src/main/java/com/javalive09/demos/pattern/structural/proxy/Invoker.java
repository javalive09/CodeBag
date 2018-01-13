package com.javalive09.demos.pattern.structural.proxy;

/**
 * 控制对一个对象的访问
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker {

    public void invoke() {
        new Proxy().show();
    }
    
}
