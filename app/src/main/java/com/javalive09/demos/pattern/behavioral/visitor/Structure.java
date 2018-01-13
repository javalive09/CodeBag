package com.javalive09.demos.pattern.behavioral.visitor;

import java.util.ArrayList;

public class Structure {

	ArrayList<Element> mList = new ArrayList<Element>();
	
	public Structure() {
		for(int i=0; i< 10; i++) {
			mList.add(new Element());
		}
	}
	
	public void doing(Visitor v) {
		for(Element e: mList) {
			e.accept(v);
		}
	}
	
}
