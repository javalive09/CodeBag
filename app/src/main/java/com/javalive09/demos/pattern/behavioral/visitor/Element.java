package com.javalive09.demos.pattern.behavioral.visitor;

import android.util.Log;

public class Element {
	
	void operate1() {
		Log.i("peter", "operate1");
	}
	
	void operate2() {
		Log.i("peter", "operate2");
	}
	
	void operate3() {
		Log.i("peter", "operate3");
	}
	
	void accept(Visitor v) {
		v.visit(this);
	}
}
