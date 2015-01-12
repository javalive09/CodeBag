package com.codebag.code.mycode.pattern.creational.abstractfactory;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.code.mycode.pattern.creational.abstractfactory.am.FactoryAm;
import com.codebag.code.mycode.pattern.creational.abstractfactory.ch.FactoryCh;

/**
 * 适用于提供产品族类的情景。
 * 
 * @author zhangrui-ms
 *
 */
public class Invoke extends MyCode {

    public Invoke(MainActivity act) {
        super(act);
    }

    @Entry
    public void invoke() {
        AbsFactory amF = new FactoryAm();
        AbsFactory chF = new FactoryCh();
        
        OutLet amO = amF.createOutlet();
        OutLet chO = chF.createOutlet();
        
        Plug amP = amF.createPlug();
        Plug chP = chF.createPlug();
        
        
    }
    
}
