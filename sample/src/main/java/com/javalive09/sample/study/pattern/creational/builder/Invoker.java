package com.javalive09.sample.study.pattern.creational.builder;

import com.javalive09.codebag.Entry;

/**
 * 
 * 适用于一个对象由多个部分组成，各个组成部分可以灵活变化的情况。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker extends Entry {

    public void invoke() {
        Product p = new Director().build(new Builder());
    }

}
