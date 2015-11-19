package com.codebag.code.mycode.test.static_method;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class main extends MyCode {

	public main(InovkedViewActivity act) {
		super(act);
	}
	
	@Entry
	public void run() {
		Test test = new Test();
		int result = test.abc(Test.a);
	}

}
