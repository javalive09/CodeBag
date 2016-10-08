package com.javalive09.sample.study.pattern.behavioral.mediator;

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
