package com.javalive09.sample.study.pattern.structural.adapter;

import com.javalive09.codebag.Entry;

/**
 * 更好的连接不兼容的接口
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends Entry {

    public void invoke() {
        new Adapter().doSomething();
    }

}
