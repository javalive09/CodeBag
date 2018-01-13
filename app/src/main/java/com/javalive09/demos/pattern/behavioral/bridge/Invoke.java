package com.javalive09.demos.pattern.behavioral.bridge;


public class Invoke {

	public void invoke() {
		Shape s = new Rect();
		Color c = new Red();
		s.draw(c);
	}
	
}
