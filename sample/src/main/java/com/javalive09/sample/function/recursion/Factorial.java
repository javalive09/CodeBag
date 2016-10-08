package com.javalive09.sample.function.recursion;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;


/**
 *  阶乘
 * @author peter
 *
 */
public class Factorial extends Entry {

	public void function() {
		int result = factorial(5);
		LogUtil.i("result = " + result);
	}
	
	private int factorial(int n) {
		if(n < 0) {
			return -1;
		}else if(n == 0) {
			return 1;
		}else if(n == 1) {
			return 1;
		}else {
			return n* factorial(n - 1);
		}
	}
	

}
