package com.codebag.code.mycode.pattern.behavioral.mediator;

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
