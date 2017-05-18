package com.javalive09.sample.study.pattern.behavioral.observer;

import java.util.ArrayList;

public class Obserable {
    
    private ArrayList<Observer> mList = new ArrayList<Observer>();
    
    public void addObserver(Observer o) {
        mList.add(o);
    }
    
    public void removeObserver(Observer o) {
        mList.remove(o);
    }
    
    public void actionAll() {
        for(Observer o: mList) {
            o.action();
        }
    }
    
}
