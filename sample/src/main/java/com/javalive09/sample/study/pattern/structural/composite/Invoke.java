package com.javalive09.sample.study.pattern.structural.composite;

import com.javalive09.codebag.Entry;

/**
 * 使对单个对象和组合对象的接口使用一致
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends Entry {

    public void invoke() {
        new ItemGroup().add(new ColorItem());
        new ItemGroup().add(new ItemGroup());
    }

}
