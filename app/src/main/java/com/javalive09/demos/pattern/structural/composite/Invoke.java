package com.javalive09.demos.pattern.structural.composite;

/**
 * 使对单个对象和组合对象的接口使用一致
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke {

    public void invoke() {
        new ItemGroup().add(new ColorItem());
        new ItemGroup().add(new ItemGroup());
    }

}
