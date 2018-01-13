package com.javalive09.demos.pattern.behavioral.mediator;

public class ClassC {
	Mediator mM;
	
	void optC(Mediator m) {
		mM = m;
	}
	
	void doing() {
		
	}
	
	void optB() {
		mM.optB();
	}
}
