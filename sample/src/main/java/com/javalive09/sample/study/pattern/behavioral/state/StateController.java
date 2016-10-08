package com.javalive09.sample.study.pattern.behavioral.state;

public class StateController {
    
    public static final int A = 1;
    public static final int B = 2;
    private int mCurStateIndex;
    private State mA;
    private State mB;
    private State mState;
    
    public StateController() {
        mA = new StateA();
        mB = new StateB();
    }
    
    public void setCurrentState(int state) {
        mCurStateIndex = state;
    }
    
    public void action() {
        switch(mCurStateIndex) {
        case A:
            mState = mA;
            break;
        case B:
            mState = mB;
            break;
        default:
            break;
        }
        mState.action();
    }
    
}
