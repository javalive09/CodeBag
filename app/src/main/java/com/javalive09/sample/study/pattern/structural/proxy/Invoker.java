package com.javalive09.sample.study.pattern.structural.proxy;

import com.javalive09.codebag.Entry;

/**
 * 控制对一个对象的访问
 * 
 * @author zhangrui-ms
 *
 */
public class Invoker extends Entry {

    public void invoke() {
        new Proxy().show();
    }
    
}
