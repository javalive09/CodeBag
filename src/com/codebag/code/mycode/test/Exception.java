package com.codebag.code.mycode.test;

import android.util.Log;

import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Exception extends MyCode {

	public Exception(MainActivity act) {
		super(act);
	}

	@Entry
	public void nullPointerException() {
		int i = 0;
		int j = 5/i;
		Log.i("peter", "j=" + j);
	}
	
}
