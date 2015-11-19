package com.codebag.code.mycode.pattern.behavioral.command;

import java.util.ArrayList;

public class Waiter {
    
    ArrayList<Food> mFoodOrders = new ArrayList<Food>();
    
    void addOrder(Food order) {
        mFoodOrders.add(order);
    }
    
    void deleteOrder(Food order) {
        mFoodOrders.remove(order);
    }
    
    void execute() {
        for(Food food : mFoodOrders) {
            food.create();
        }
    }
    
}
