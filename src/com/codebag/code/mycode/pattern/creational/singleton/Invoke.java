package com.codebag.code.mycode.pattern.creational.singleton;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Invoke extends MyCode {

    public Invoke(MainActivity act) {
        super(act);
    }

    @Entry
    public void invoke() {
        SingleTon.getInstance().doSomething();
    }
}
