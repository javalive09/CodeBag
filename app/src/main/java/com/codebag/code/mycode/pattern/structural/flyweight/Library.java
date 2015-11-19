package com.codebag.code.mycode.pattern.structural.flyweight;

import java.util.HashMap;

public class Library {
    
    HashMap<String, Book> books = new HashMap<String, Book>();
    
    public Book getBook(String type){
        if(!books.containsKey(type)) {
            if("story".equals(type)) {
                books.put(type, new StoryBook());
            }else if("technology".equals(type)) {
                books.put(type, new TechnologyBook());
            }
        }
        return books.get(type);
    }
}
