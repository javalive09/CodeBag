package com.javalive09.demos.pattern.behavioral.mediator;

public class ClassA {
	Mediator mM;
	
	void optA(Mediator m) {
		mM = m;
	}
	
	void doing() {
		
	}
	
	void optC() {
		mM.optC();
	}
}
