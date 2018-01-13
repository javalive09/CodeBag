package com.javalive09.demos.pattern.creational.builder;

public class Director {

    public Product build(AbsBuilder builder) {
        return builder.buildHead().buildBody().buildFeet().create();
    }
    
}
