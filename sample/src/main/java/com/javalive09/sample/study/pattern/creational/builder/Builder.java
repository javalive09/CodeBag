package com.javalive09.sample.study.pattern.creational.builder;

public class Builder implements AbsBuilder{

    Product mProduct;
    
    public Builder() {
        mProduct = new Product();
    }
    
    public Builder buildHead() {
        return this;
    }
    
    public Builder buildBody() {
        return this;
    }
    
    public Builder buildFeet() {
        return this;
    }
    
    public Product create() {
        return mProduct;
    }
    
}
