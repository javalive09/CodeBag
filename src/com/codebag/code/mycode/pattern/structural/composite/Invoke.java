package com.codebag.code.mycode.pattern.structural.composite;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

/**
 * 使对单个对象和组合对象的接口使用一致
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends MyCode {

    public Invoke(MainActivity act) {
        super(act);
    }
    
    @Entry
    public void invoke() {
        new ItemGroup().add(new ColorItem());
        new ItemGroup().add(new ItemGroup());
    }

}
