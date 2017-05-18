package com.javalive09.sample.study.pattern.creational.abstractfactory.ch;


import com.javalive09.sample.study.pattern.creational.abstractfactory.AbsFactory;
import com.javalive09.sample.study.pattern.creational.abstractfactory.OutLet;
import com.javalive09.sample.study.pattern.creational.abstractfactory.Plug;

public class FactoryCh implements AbsFactory {

    @Override
    public OutLet createOutlet() {
        return new OutLetCh();
    }

    @Override
    public Plug createPlug() {
        return new PlugCh();
    }

}
