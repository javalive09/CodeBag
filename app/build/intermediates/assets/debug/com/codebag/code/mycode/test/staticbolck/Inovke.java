package com.codebag.code.mycode.test.staticbolck;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Inovke extends MyCode {

	public Inovke(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void newObject() {
		new FooSon();
	}

}
