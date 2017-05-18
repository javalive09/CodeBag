package com.javalive09.sample.study.pattern.creational.builder;

public interface AbsBuilder {

    public Builder buildHead();
    
    public Builder buildBody();
    
    public Builder buildFeet();
    
    public Product create();
    
}
