package com.codebag.code.mycode.pattern.structural.composite;

import java.util.ArrayList;

public class ItemGroup extends Item {
    
    private ArrayList<Item> items = new ArrayList<Item>();
    
    public void add(Item item) {
        items.add(item);
    }
    
    public void remove(Item item) {
        items.remove(item);
    }
    
    
    
}
