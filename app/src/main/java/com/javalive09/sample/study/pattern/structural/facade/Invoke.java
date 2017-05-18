package com.javalive09.sample.study.pattern.structural.facade;

import com.javalive09.codebag.Entry;

/**
 * 为复杂系统提供一个简单的接口
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends Entry {

    public void invoke() {
        Facade f = new Facade();
        f.actionA();
        f.actionB();
        f.actionC();
    }

}
