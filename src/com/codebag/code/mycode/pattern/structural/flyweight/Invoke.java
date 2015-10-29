package com.codebag.code.mycode.pattern.structural.flyweight;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

/**
 * 以共享的方式实现内存最小化。
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
        Library lib = new Library();
        for(int i = 0; i< 10; i++) {
            Book book = lib.getBook("story");
            book.read();
        }
    }

}
