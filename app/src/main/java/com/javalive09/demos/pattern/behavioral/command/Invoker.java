package com.javalive09.demos.pattern.behavioral.command;


public class Invoker{

    public void invoke() {
        Waiter waiter = new Waiter();
        Food a = new FoodA();
        Food b = new FoodB();
        waiter.addOrder(a);
        waiter.addOrder(b);
        waiter.execute();
    }

}
