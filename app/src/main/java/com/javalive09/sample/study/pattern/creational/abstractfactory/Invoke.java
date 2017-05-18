package com.javalive09.sample.study.pattern.creational.abstractfactory;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.study.pattern.creational.abstractfactory.am.FactoryAm;
import com.javalive09.sample.study.pattern.creational.abstractfactory.ch.FactoryCh;

/**
 * 适用于提供产品族类的情景。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends Entry {

    public void invoke() {
        AbsFactory amF = new FactoryAm();
        AbsFactory chF = new FactoryCh();
        
        OutLet amO = amF.createOutlet();
        OutLet chO = chF.createOutlet();
        
        Plug amP = amF.createPlug();
        Plug chP = chF.createPlug();
        
        
    }
    
}
