package com.javalive09.demos.pattern.structural.decorator;

/**
 * 一个对象通过叠加其他对象来增加这个对象的功能。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke {

    public void invoke() {
        new BlueBuffer(new Hero()).attack();
        new RedBuffer(new Hero()).attack();
        new BlueBuffer(new RedBuffer(new Hero())).attack();
    }
}
