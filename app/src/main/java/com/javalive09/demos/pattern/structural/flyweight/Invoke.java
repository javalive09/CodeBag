package com.javalive09.demos.pattern.structural.flyweight;


/**
 * 以共享的方式实现内存最小化。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke {

    public void invoke() {
        Library lib = new Library();
        for(int i = 0; i< 10; i++) {
            Book book = lib.getBook("story");
            book.read();
        }
    }

}
