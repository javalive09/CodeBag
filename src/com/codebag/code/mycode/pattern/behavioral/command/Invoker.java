package com.codebag.code.mycode.pattern.behavioral.command;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoker extends MyCode {

    public Invoker(InovkedViewActivity act) {
        super(act);
    }
    
    @Entry
    public void invoke() {
        Waiter waiter = new Waiter();
        Food a = new FoodA();
        Food b = new FoodB();
        waiter.addOrder(a);
        waiter.addOrder(b);
        waiter.execute();
    }

}
