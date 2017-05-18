package com.javalive09.sample.function.exception;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.logger.Log;

public class Exception extends Entry {

	public void exception() {
		int i = 0;
		int j = 5/i;
		Log.i("peter", "j=" + j);
	}
	
}
