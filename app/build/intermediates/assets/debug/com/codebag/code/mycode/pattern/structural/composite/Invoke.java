package com.codebag.code.mycode.pattern.structural.composite;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 使对单个对象和组合对象的接口使用一致
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends MyCode {

    public Invoke(InovkedViewActivity act) {
        super(act);
    }
    
    @Entry
    public void invoke() {
        new ItemGroup().add(new ColorItem());
        new ItemGroup().add(new ItemGroup());
    }

}
