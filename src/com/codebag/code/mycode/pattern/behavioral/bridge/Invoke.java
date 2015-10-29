package com.codebag.code.mycode.pattern.behavioral.bridge;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Invoke extends MyCode {

	public Invoke(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void invoke() {
		Shape s = new Rect();
		Color c = new Red();
		s.draw(c);
	}
	
}
