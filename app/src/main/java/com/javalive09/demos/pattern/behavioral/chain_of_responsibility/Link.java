package com.javalive09.demos.pattern.behavioral.chain_of_responsibility;

public class Link {
    
    private Link mNextLink;
    
    boolean hasNext(){
        return mNextLink == null ? false : true;
    }
    
    Link next(){
        return mNextLink;
    }
    
    void setNext(Link link){
        mNextLink = link;
    }
    
    public void action(){
        if(hasNext()){
            next().action();
        }
    }
}
