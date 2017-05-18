package com.javalive09.sample.study.pattern.behavioral.bridge;


import com.javalive09.codebag.Entry;

public class Invoke extends Entry {

	public void invoke() {
		Shape s = new Rect();
		Color c = new Red();
		s.draw(c);
	}
	
}
