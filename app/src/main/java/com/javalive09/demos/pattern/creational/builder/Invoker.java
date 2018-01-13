package com.javalive09.demos.pattern.creational.builder;



/**
 * 
 * 适用于一个对象由多个部分组成，各个组成部分可以灵活变化的情况。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker {

    public void invoke() {
        Product p = new Director().build(new Builder());
    }

}
