package com.codebag.code.mycode.test.staticbolck;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Inovke extends MyCode {

	public Inovke(MainActivity act) {
		super(act);
	}
	
	@Entry
	public void newObject() {
		new Foo();
	}

}
