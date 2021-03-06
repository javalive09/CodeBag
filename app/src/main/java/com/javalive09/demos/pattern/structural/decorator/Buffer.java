package com.javalive09.demos.pattern.structural.decorator;

public class Buffer implements Attacker {

    Attacker mAttacker;
    
    public Buffer(Attacker a) {
        mAttacker = a;
    }
    
    @Override
    public void attack() {
        mAttacker.attack();
    }

}
