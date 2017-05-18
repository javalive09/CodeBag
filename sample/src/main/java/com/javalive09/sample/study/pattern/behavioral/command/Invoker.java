package com.javalive09.sample.study.pattern.behavioral.command;


import com.javalive09.codebag.Entry;

public class Invoker extends Entry {

    public void invoke() {
        Waiter waiter = new Waiter();
        Food a = new FoodA();
        Food b = new FoodB();
        waiter.addOrder(a);
        waiter.addOrder(b);
        waiter.execute();
    }

}
