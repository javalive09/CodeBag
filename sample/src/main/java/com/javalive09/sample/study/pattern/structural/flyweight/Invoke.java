package com.javalive09.sample.study.pattern.structural.flyweight;

import com.javalive09.codebag.Entry;

/**
 * 以共享的方式实现内存最小化。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends Entry {

    public void invoke() {
        Library lib = new Library();
        for(int i = 0; i< 10; i++) {
            Book book = lib.getBook("story");
            book.read();
        }
    }

}
