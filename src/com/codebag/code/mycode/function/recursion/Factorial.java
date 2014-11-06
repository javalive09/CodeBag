package com.codebag.code.mycode.function.recursion;

import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;
import com.codebag.bag.Entry;
import com.codebag.code.mycode.utils.Log;


/**
 *  阶乘
 * @author peter
 *
 */
public class Factorial extends MyCode {

	public Factorial(MainActivity context) {
		super(context);
	}
	
	@Entry()
	public void function() {
		int result = factorial(5);
		Log.addLog("peter", this, "result = " + result);
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
