package com.codebag.code.mycode.pattern.behavioral.mediator;

public class Mediator {
	ClassA mA;
	ClassB mB;
	ClassC mC;
	
	public void setA(ClassA a) {
		mA = a;
	}
	
	public void setB(ClassB b) {
		mB = b;
	}
	
	public void setC(ClassC c) {
		mC = c;
	}
	
	public void optA() {
		mA.doing();
	}
	
	public void optB() {
		mB.doing();
	}
	
	public void optC() {
		mC.doing();
	}
}
