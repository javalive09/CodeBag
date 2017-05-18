package com.javalive09.sample.study.pattern.structural.decorator;

import com.javalive09.codebag.Entry;

/**
 * 一个对象通过叠加其他对象来增加这个对象的功能。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends Entry {

    public void invoke() {
        new BlueBuffer(new Hero()).attack();
        new RedBuffer(new Hero()).attack();
        new BlueBuffer(new RedBuffer(new Hero())).attack();
    }
}
