package com.codebag.code.mycode.pattern.creational.abstractfactory.am;

import com.codebag.code.mycode.pattern.creational.abstractfactory.AbsFactory;
import com.codebag.code.mycode.pattern.creational.abstractfactory.OutLet;
import com.codebag.code.mycode.pattern.creational.abstractfactory.Plug;

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
