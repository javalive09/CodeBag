package com.codebag.code.mycode.test;

import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Exception extends MyCode {

	public Exception(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void nullPointerException() {
		int i = 0;
		int j = 5/i;
		Log.i("peter", "j=" + j);
	}
	
}
