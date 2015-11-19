package com.codebag.code.mycode.pattern.creational.abstractfactory.ch;

import com.codebag.code.mycode.pattern.creational.abstractfactory.AbsFactory;
import com.codebag.code.mycode.pattern.creational.abstractfactory.OutLet;
import com.codebag.code.mycode.pattern.creational.abstractfactory.Plug;

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
