package com.javalive09.demos.pattern.creational.abstractfactory.am;


import com.javalive09.demos.pattern.creational.abstractfactory.AbsFactory;
import com.javalive09.demos.pattern.creational.abstractfactory.OutLet;
import com.javalive09.demos.pattern.creational.abstractfactory.Plug;

public class FactoryAm implements AbsFactory {

    @Override
    public OutLet createOutlet() {
        return new OutLetAm();
    }

    @Override
    public Plug createPlug() {
        return new PlugAm();
    }


}
