package com.javalive09.demos.pattern.behavioral.mediator;

public class ClassB {
	Mediator mM;
	
	void optB(Mediator m) {
		mM = m;
	}
	
	void doing() {
		
	}
	
	void optA() {
		mM.optA();
	}
}
