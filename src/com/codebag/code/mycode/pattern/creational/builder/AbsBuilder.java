package com.codebag.code.mycode.pattern.creational.builder;

public interface AbsBuilder {

    public Builder buildHead();
    
    public Builder buildBody();
    
    public Builder buildFeet();
    
    public Product create();
    
}
